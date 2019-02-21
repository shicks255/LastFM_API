# LastFM_API
API wrapper to use last.fm

<b>Searching for Artist</b></b>
create an <b>ArtistQueryBuilder</b> like...<br/>
ArtistQueryBuilder query = new ArtistQueryBuilder.Builder().artistName("Pink Floyd").build();</br>
then create and use a <b>ArtistSearcher</b> like<br/>
ArtistSearcher searcher = new ArtistSearcher();<br/>
searcher.searchForArtists(query);<br/>
returns List<Artist><br/>

<b>Searching for Albums</b><br/>
create an <b>AlbumQueryBuilder</b> like...<br/>
AlbumQueryBuilder query = new AlbumQueryBuilder.Builder().albumName("Dark Side Of the Moon"").setLimit(1).build();<br/>
then create and use a <b>AlbumSearcher</b> like<br/>
AlbumSearcher searcher = new AlbumSearcher();<br/>
searcher.
