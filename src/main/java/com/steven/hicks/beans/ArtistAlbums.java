package com.steven.hicks.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistAlbums
{
    private String m_name = "";
    private String m_mbid = "";

    private Album.Image[] image;

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public String getMbid()
    {
        return m_mbid;
    }

    public void setMbid(String mbid)
    {
        m_mbid = mbid;
    }

    public Album.Image[] getImage()
    {
        return image;
    }

    public void setImage(Album.Image[] image)
    {
        this.image = image;
    }
}
