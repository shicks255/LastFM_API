
import com.steven.hicks.beans.Album;
import com.steven.hicks.beans.Artist;
import com.steven.hicks.logic.ArtistQueryBuilder;
import com.steven.hicks.logic.ArtistSearcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ArtistSearchTest
{

    @Test
    public void artistSearchTest1()
    {
        ArtistQueryBuilder builder = new ArtistQueryBuilder.Builder().artistName("owen").setLimit(2).build();
        ArtistSearcher searcher = new ArtistSearcher();
        List<Artist> artists = searcher.search(builder);

        Artist fullArtist = searcher.getFullArtist(artists.get(0));

        List<Album> albums = searcher.getAlbums(new ArtistQueryBuilder.Builder().mbid(fullArtist.getMbid()).build());

        System.out.println(fullArtist);
        System.out.println(albums);

//        artists.forEach(System.out::println);
    }

}
