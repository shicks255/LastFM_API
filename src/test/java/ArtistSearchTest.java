
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

        ArtistQueryBuilder builder = new ArtistQueryBuilder.Builder().artistName("American Football").build();
        ArtistSearcher searcher = new ArtistSearcher();
        List<Artist> artists = searcher.search(builder);

        artists.forEach(System.out::println);
    }

}
