package com.steven.hicks.logic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steven.hicks.MissingConfigKeyException;
import com.steven.hicks.NoConfigException;
import com.steven.hicks.beans.Album;
import com.steven.hicks.beans.Artist;
import com.steven.hicks.beans.ArtistAlbums;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class ArtistSearcher
{
    private static ObjectMapper m_objectMapper = new ObjectMapper();
    private ResourceBundle config;

    public ArtistSearcher()
    {
        try {
            config = ResourceBundle.getBundle("config");
        } catch (Exception e) {
            throw new NoConfigException("Invalid or missing config.properties file");
        }
        try {
            config.getString("api_key");
        } catch (Exception E) {
            throw new MissingConfigKeyException("Missing config property API_KEY");
        }
    }

    public List<Artist> search(ArtistQueryBuilder query)
    {
        StringBuilder apiEndpoint = new StringBuilder("https://ws.audioscrobbler.com/2.0/?method=artist.search&artist=");

        apiEndpoint.append(query.getArtist());
        apiEndpoint.append("&limit=" + query.getLimit());
        apiEndpoint.append("&page=" + query.getPage());

        apiEndpoint.append("&api_key=" + config.getString("api_key") +"&format=json");

        List<Artist> artistList = Collections.emptyList();
        try
        {
            URL url = new URL(apiEndpoint.toString());
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

            connection.setRequestProperty("accept", "application/json");
            connection.setRequestMethod("GET");

            StringBuilder data = new StringBuilder();
            String input;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((input = in.readLine()) != null)
                data.append(input);

            JsonNode node = m_objectMapper.readTree(data.toString());
            JsonNode inner = node.get("results").get("artistmatches").get("artist");

            Artist[] artists = m_objectMapper.treeToValue(inner, Artist[].class);
            artistList = Arrays.asList(artists);
        }
        catch (Exception e)
        { }

        return artistList;
    }

    public List<ArtistAlbums> getAlbums(ArtistQueryBuilder query)
    {
        StringBuilder apiEndpoint = new StringBuilder("https://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&mbid=");

        apiEndpoint.append(query.getMbid());
        apiEndpoint.append("&limit=" + query.getLimit());
        apiEndpoint.append("&page=" + query.getPage());
        apiEndpoint.append("&api_key=" + config.getString("api_key") +"&format=json");

        List<ArtistAlbums> albumList = Collections.emptyList();
        try
        {
            URL url = new URL(apiEndpoint.toString());
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

            connection.setRequestProperty("accept", "application/json");
            connection.setRequestMethod("GET");

            StringBuilder data = new StringBuilder();
            String input;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((input = in.readLine()) != null)
                data.append(input);

            m_objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            JsonNode node = m_objectMapper.readTree(data.toString());
            JsonNode inner = node.get("topalbums").get("album");
            List<ArtistAlbums> artistAlbums = m_objectMapper.readValue(inner.toString(), new TypeReference<List<ArtistAlbums>>() {});
            albumList = artistAlbums;
        }
        catch (Exception e)
        { }

        return albumList;
    }

    public Artist getFullArtist(String mbid)
    {
        StringBuilder apiEndpoint = new StringBuilder("https://ws.audioscrobbler.com/2.0/?method=artist.getInfo&mbid=");
        apiEndpoint.append(mbid);
        apiEndpoint.append("&api_key=" + config.getString("api_key") +"&format=json");

        Artist fullArtist = null;
        try
        {
            URL url = new URL(apiEndpoint.toString());
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

            connection.setRequestProperty("accept", "application/json");
            connection.setRequestMethod("GET");

            StringBuilder data = new StringBuilder();
            String input;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((input = in.readLine()) != null)
                data.append(input);

            m_objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            JsonNode node = m_objectMapper.readTree(data.toString());
            JsonNode inner = node.get("artist");
            Artist aa = m_objectMapper.treeToValue(inner, Artist.class);
            fullArtist = aa;
//            fullArtist.setListeners(a.getListeners());
        }
        catch (Exception e)
        {}

        return fullArtist;
    }

}
