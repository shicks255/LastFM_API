package com.steven.hicks.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.steven.hicks.logic.ArtistSearcher;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist
{
    private String m_name = "";
    private String m_mbid = "";
    private String m_url = "";
    private String m_imageSmall = "";
    private String m_imageMedium = "";
    private String m_imageLarge = "";

    private ArtistSearcher.Image image;

    private int    m_listeners;
    private long   m_plays;

    private String m_bioSummary = "";
    private String m_bioContent = "";

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

    public String getUrl()
    {
        return m_url;
    }

    public void setUrl(String url)
    {
        m_url = url;
    }

    public String getImageSmall()
    {
        return m_imageSmall;
    }

    public void setImageSmall(String imageSmall)
    {
        m_imageSmall = imageSmall;
    }

    public String getImageMedium()
    {
        return m_imageMedium;
    }

    public void setImageMedium(String imageMedium)
    {
        m_imageMedium = imageMedium;
    }

    public String getImageLarge()
    {
        return m_imageLarge;
    }

    public void setImageLarge(String imageLarge)
    {
        m_imageLarge = imageLarge;
    }

    public int getListeners()
    {
        return m_listeners;
    }

    public void setListeners(int listeners)
    {
        m_listeners = listeners;
    }

    public long getPlays()
    {
        return m_plays;
    }

    public void setPlays(long plays)
    {
        m_plays = plays;
    }

    public String getBioSummary()
    {
        return m_bioSummary;
    }

    public void setBioSummary(String bioSummary)
    {
        m_bioSummary = bioSummary;
    }

    public String getBioContent()
    {
        return m_bioContent;
    }

    public void setBioContent(String bioContent)
    {
        m_bioContent = bioContent;
    }

    public ArtistSearcher.Image getImage()
    {
        return image;
    }

    public void setImage(ArtistSearcher.Image image)
    {
        this.image = image;
    }
}
