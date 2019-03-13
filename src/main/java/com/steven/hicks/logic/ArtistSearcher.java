package com.steven.hicks.logic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steven.hicks.MissingConfigKeyException;
import com.steven.hicks.NoConfigException;
import com.steven.hicks.beans.artist.Artist;
import com.steven.hicks.beans.ArtistAlbums;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

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

    /**
     *
     * Returns a list of <Artist>Artist</Artist> given an <ArtistQueryBuilder>ArtistQueryBuilder</ArtistQueryBuilder>
     *
     * @param query - <ArtistQueryBuilder>ArtistQueryBuilder</ArtistQueryBuilder> taking a name, mbid, limit, and page number
     * @return List<<Artist>Artist</Artist>>
     */
    public List<Artist> searchForArtists(ArtistQueryBuilder query)
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
            try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8")))
            {
                while ((input = in.readLine()) != null)
                    data.append(input);
            }

            JsonNode node = m_objectMapper.readTree(data.toString());
            JsonNode inner = node.get("results").get("artistmatches").get("artist");

            Artist[] artists = m_objectMapper.treeToValue(inner, Artist[].class);
            artistList = Arrays.asList(artists);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return artistList;
    }

    /**
     * @NonNull(query.mbid)
     *
     * Takes an <ArtistQueryBuilder>ArtistQueryBuilder</ArtistQueryBuilder> with an MBID and returns a list of
     * <ArtistAlbums>ArtistAlbums</ArtistAlbums>, which are dumb representations of the fuller <Album>Album</Album> object.
     * Can then use <AlbumSearcher>AlbumSearcher</AlbumSearcher> to get the <Album>Album</Album> from the <ArtistAlbum>ArtistAlbum</ArtistAlbum>
     *
     * @param query - an <ArtistQueryBuilder>ArtistQueryBuilder</ArtistQueryBuilder> that <b>MUST</b> have the MBID field.
     * @return - List<<ArtistAlbums>ArtistAlbums</ArtistAlbums>>
     */
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
            try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8")))
            {
                while ((input = in.readLine()) != null)
                    data.append(input);
            }

            m_objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            JsonNode node = m_objectMapper.readTree(data.toString());
            JsonNode inner = node.get("topalbums").get("album");
            List<ArtistAlbums> artistAlbums = m_objectMapper.readValue(inner.toString(), new TypeReference<List<ArtistAlbums>>() {});
            artistAlbums.removeIf(x -> x.getImage().length == 0);
            albumList = artistAlbums;
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return albumList;
    }

    /**
     * Given an MBID, returns an 'full' artist, which includes tags, and bio, in addition to everything the searchForArtists()
     * results gives you.
     *
     * @param mbid
     * @return <Artist>Artist</Artist>
     */
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
            try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));)
            {
                while ((input = in.readLine()) != null)
                    data.append(input);
            }

            m_objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            JsonNode node = m_objectMapper.readTree(data.toString());
            JsonNode inner = node.get("artist");
            Artist aa = m_objectMapper.treeToValue(inner, Artist.class);
            fullArtist = aa;
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return fullArtist;
    }

}
