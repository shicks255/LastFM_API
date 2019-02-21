# LastFM_API
API wrapper to use last.fm

<b>Searching for Artist</b></b>
create an <b>ArtistQueryBuilder</b> like...
ArtistQueryBuilder query = new ArtistQueryBuilder.Builder().artistName("Pink Floyd").build();
then create and use a <b>ArtistSearcher</b> like
ArtistSearcher searcher = new ArtistSearcher();
searcher.searchForArtists(query);
returns List<Artist>

<b>Searching for Albums</b><br/>
create an <b>AlbumQueryBuilder</b> like...
AlbumQueryBuilder query = new AlbumQueryBuilder.Builder().albumName("Dark Side Of the Moon"").setLimit(1).build();
then create and use a <b>AlbumSearcher</b> like
AlbumSearcher searcher = new AlbumSearcher();
searcher.
