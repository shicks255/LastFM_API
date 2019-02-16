package com.steven.hicks.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album
{
    private String m_name = "";
    private String m_artist = "";
    private long   m_id;
    private String m_mbid = "";
    private String m_url = "";
    private String m_releasedate = "";
    private Image[] image;
    private int    m_listeners;
    private long   m_playCount;
    private Tracks  tracks;

    @Override
    public String toString()
    {
        return m_name + " " + m_artist + " ";
    }

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

    public Image[] getImage()
    {
        return image;
    }

    public void setImage(Image[] image)
    {
        this.image = image;
    }

    public Tracks getTracks()
    {
        return tracks;
    }

    public void setTracks(Tracks tracks)
    {
        this.tracks = tracks;
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


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Image
    {
        String text = "";
        String size = "";

        public String getSize()
        {
            return size;
        }

        public void setSize(String size)
        {
            this.size = size;
        }

        @JsonProperty("#text")
        public String getText()
        {
            return text;
        }

        public void setText(String text)
        {
            this.text = text;
        }
    }

    public static class Tracks
    {
        private Track[] track;

        public Track[] getTrack()
        {
            return track;
        }

        public void setTrack(Track[] track)
        {
            this.track = track;
        }
    }

    public static class Attr
    {
        private String rank = "";

        public String getRank()
        {
            return rank;
        }

        public void setRank(String rank)
        {
            this.rank = rank;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Track
    {
        private String m_rank = "";
        private Attr attr;
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

        @JsonProperty("@attr")
        public Attr getAttr()
        {
            return attr;
        }

        public void setAttr(Attr attr)
        {
            this.attr = attr;
        }
    }
}
