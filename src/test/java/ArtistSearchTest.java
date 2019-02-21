import com.steven.hicks.beans.Album;
import com.steven.hicks.beans.Artist;
import com.steven.hicks.beans.ArtistAlbums;
import com.steven.hicks.logic.AlbumSearcher;
import com.steven.hicks.logic.ArtistQueryBuilder;
import com.steven.hicks.logic.ArtistSearcher;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistSearchTest
{
    @Test
    public void basicTest()
    {
        ArtistQueryBuilder builder = new ArtistQueryBuilder.Builder().artistName("Pink Floyd").build();
        ArtistSearcher searcher = new ArtistSearcher();
        List<Artist> artists = searcher.searchForArtists(builder);
        assertTrue("Cant query Pink Floyd", artists!=null && artists.size() > 0);
    }

    @Test
    public void artistSearchTest1()
    {
        String artistName = "american football";
        ArtistQueryBuilder builder = new ArtistQueryBuilder.Builder().artistName(artistName).setLimit(1).build();
        ArtistSearcher searcher = new ArtistSearcher();
        List<Artist> artists = searcher.searchForArtists(builder);

        Artist fullArtist = searcher.getFullArtist(artists.get(0).getMbid());
        AlbumSearcher albumSearcher = new AlbumSearcher();

        List<ArtistAlbums> albums = searcher.getAlbums(new ArtistQueryBuilder.Builder().mbid(fullArtist.getMbid()).build());
        List<Album> fullAlbums = albums.stream().map(x -> albumSearcher.getFullAlbum(x.getMbid())).collect(Collectors.toList());

        assertTrue(artistName + " has albums", albums.size() > 0);
        assertTrue(artistName + " has albums that were able to get full", fullAlbums.size() > 0);
    }

}
