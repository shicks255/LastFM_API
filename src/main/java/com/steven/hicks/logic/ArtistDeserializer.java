package com.steven.hicks.logic;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.steven.hicks.beans.Artist;

import java.io.IOException;

public class ArtistDeserializer extends StdDeserializer<Artist>
{
    public ArtistDeserializer()
    {
        this(null);
    }

    public ArtistDeserializer(Class<?> v)
    {
        super(v);
    }

    @Override
    public Artist deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException
    {
        Artist artist = new Artist();
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);



        return artist;
    }
}
