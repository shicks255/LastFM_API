package com.steven.hicks.logic;

public class AlbumQueryBuilder
{
    private String m_album = "";
    private int    m_limit = 30;
    private int    m_page = 1;

    public static class Builder
    {
        private String name = "";
        private int    limit = 30;
        private int    page = 1;

        public AlbumQueryBuilder.Builder artistName(String name)
        {
            this.name = name.replace(" ", "%20");
            return this;
        }

        public AlbumQueryBuilder.Builder setLimit(int limit)
        {
            this.limit = limit;
            return this;
        }

        public AlbumQueryBuilder.Builder setPage(int page)
        {
            this.page = page;
            return this;
        }

        public AlbumQueryBuilder build()
        {
            AlbumQueryBuilder builder = new AlbumQueryBuilder();
            builder.m_album = this.name;
            builder.m_limit = this.limit;
            builder.m_page = this.page;

            return builder;
        }
    }
}
