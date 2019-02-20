import com.steven.hicks.beans.Album;
import com.steven.hicks.beans.Artist;
import com.steven.hicks.beans.ArtistAlbums;
import com.steven.hicks.logic.AlbumSearcher;
import com.steven.hicks.logic.ArtistQueryBuilder;
import com.steven.hicks.logic.ArtistSearcher;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistSearchTest
{

    @Test
    public void artistSearchTest1()
    {
        ArtistQueryBuilder builder = new ArtistQueryBuilder.Builder().artistName("american football").setLimit(1).build();
        ArtistSearcher searcher = new ArtistSearcher();
        List<Artist> artists = searcher.searchForArtists(builder);

        Artist fullArtist = searcher.getFullArtist(artists.get(0).getMbid());
        AlbumSearcher albumSearcher = new AlbumSearcher();

        List<ArtistAlbums> albums = searcher.getAlbums(new ArtistQueryBuilder.Builder().mbid(fullArtist.getMbid()).build());
        List<Album> theAlbums = albums.stream().map(x -> albumSearcher.getFullAlbum(x.getMbid())).collect(Collectors.toList());

        theAlbums.forEach(System.out::println);
    }

}
