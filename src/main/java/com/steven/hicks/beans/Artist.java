package com.steven.hicks.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist
{
    private String m_name = "";
    private String m_mbid = "";
    private String m_url = "";

    private Image[] image;
    private Tags tags;
    private Bio bio;

    private int    m_listeners;

    @Override
    public String toString()
    {
        return m_name + " " + m_listeners + " "  + m_url + " " + m_mbid;
    }

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

    public int getListeners()
    {
        return m_listeners;
    }

    public void setListeners(int listeners)
    {
        m_listeners = listeners;
    }

    public Image[] getImage()
    {
        return image;
    }

    public void setImage(Image[] image)
    {
        this.image = image;
    }

    public Bio getBio()
    {
        return bio;
    }

    public void setBio(Bio bio)
    {
        this.bio = bio;
    }

    public Tags getTags()
    {
        return tags;
    }

    public void setTags(Tags tags)
    {
        this.tags = tags;
    }

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

    public static class Tags
    {
        private Tag[] tag;

        public Tag[] getTag()
        {
            return tag;
        }

        public void setTag(Tag[] tag)
        {
            this.tag = tag;
        }
    }

    public static class Tag
    {
        private String name = "";
        private String url = "";

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Bio
    {
        private String published = "";
        private String summary = "";
        private String content = "";

        public String getPublished()
        {
            return published;
        }

        public void setPublished(String published)
        {
            this.published = published;
        }

        public String getSummary()
        {
            return summary;
        }

        public void setSummary(String summary)
        {
            this.summary = summary;
        }

        public String getContent()
        {
            return content;
        }

        public void setContent(String content)
        {
            this.content = content;
        }
    }
}
