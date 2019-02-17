package com.steven.hicks.logic;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steven.hicks.MissingConfigKeyException;
import com.steven.hicks.NoConfigException;
import com.steven.hicks.beans.Album;
import com.steven.hicks.beans.Artist;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class AlbumSearcher
{
    private static ObjectMapper m_objectMapper = new ObjectMapper();
    private ResourceBundle config;

    public AlbumSearcher()
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

    public List<Album> search(AlbumQueryBuilder query)
    {
        StringBuilder apiEndpoint = new StringBuilder("https://ws.audioscrobbler.com/2.0/?method=album.search&album=");

        apiEndpoint.append(query.getAlbum());
        apiEndpoint.append("&limit=" + query.getLimit());
        apiEndpoint.append("&page=" + query.getPage());

        apiEndpoint.append("&api_key=" + config.getString("api_key") +"&format=json");

        List<Album> albumList = Collections.emptyList();
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

//            System.out.println(data);
            JsonNode node = m_objectMapper.readTree(data.toString());
            JsonNode inner = node.get("results").get("albummatches").get("album");
//            System.out.println(inner);

            Album[] artists = m_objectMapper.treeToValue(inner, Album[].class);
            albumList = Arrays.asList(artists);
        }
        catch (Exception e)
        { }

        return albumList;
    }

    public Album getFullAlbum(Album a)
    {
        StringBuilder apiEndpoint = new StringBuilder("https://ws.audioscrobbler.com/2.0/?method=album.getInfo&mbid=");
        apiEndpoint.append(a.getMbid());
        apiEndpoint.append("&api_key=" + config.getString("api_key") +"&format=json");

        Album fullAlbum = null;
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

            System.out.println(data);
            JsonNode node = m_objectMapper.readTree(data.toString());
            JsonNode inner = node.get("album");
            Album aa = m_objectMapper.treeToValue(inner, Album.class);
            fullAlbum = aa;
//            fullAlbum.setListeners(a.getListeners());
        }
        catch (Exception e)
        {}

        return fullAlbum;
    }
}
