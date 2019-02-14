package com.steven.hicks.logic;

public class ArtistQueryBuilder
{
    private String m_artist = "";
    private int    m_limit = 30;
    private int    m_page = 1;

    private ArtistQueryBuilder()
    {}


    public static class Builder
    {
        private String name = "";
        private int    limit = 30;
        private int    page = 1;

        public Builder artistName(String name)
        {
            this.name = name.replace(" ", "%20");
            return this;
        }

        public Builder setLimit(int limit)
        {
            this.limit = limit;
            return this;
        }

        public Builder setPage(int page)
        {
            this.page = page;
            return this;
        }

        public ArtistQueryBuilder build()
        {
            ArtistQueryBuilder builder = new ArtistQueryBuilder();
            builder.m_artist = this.name;
            builder.m_limit = this.limit;
            builder.m_page = this.page;

            return builder;
        }
    }


}
