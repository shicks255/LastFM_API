package com.steven.hicks.beans;

import java.util.Collections;
import java.util.List;

public class Album
{
    private String m_name = "";
    private String m_artist = "";
    private long   m_id;
    private String m_mbid = "";
    private String m_url = "";
    private String m_releasedate = "";
    private String m_imageSmall = "";
    private String m_imageMedium = "";
    private String m_imageLarge = "";
    private int    m_listeners;
    private long   m_playCount;

    private List<Track> m_tracks = Collections.emptyList();

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public String getArtist()
    {
        return m_artist;
    }

    public void setArtist(String artist)
    {
        m_artist = artist;
    }

    public long getId()
    {
        return m_id;
    }

    public void setId(long id)
    {
        m_id = id;
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

    public String getReleasedate()
    {
        return m_releasedate;
    }

    public void setReleasedate(String releasedate)
    {
        m_releasedate = releasedate;
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

    public long getPlayCount()
    {
        return m_playCount;
    }

    public void setPlayCount(long playCount)
    {
        m_playCount = playCount;
    }

    public List<Track> getTracks()
    {
        return m_tracks;
    }

    public void setTracks(List<Track> tracks)
    {
        m_tracks = tracks;
    }

    public static class Track
    {
        private String m_rank = "";
        private String m_name = "";
        private int    m_duration;

        public String getRank()
        {
            return m_rank;
        }

        public void setRank(String rank)
        {
            m_rank = rank;
        }

        public String getName()
        {
            return m_name;
        }

        public void setName(String name)
        {
            m_name = name;
        }

        public int getDuration()
        {
            return m_duration;
        }

        public void setDuration(int duration)
        {
            m_duration = duration;
        }
    }
}
