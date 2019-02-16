
import com.steven.hicks.beans.Album;
import com.steven.hicks.beans.Artist;
import com.steven.hicks.logic.AlbumQueryBuilder;
import com.steven.hicks.logic.AlbumSearcher;
import com.steven.hicks.logic.ArtistQueryBuilder;
import com.steven.hicks.logic.ArtistSearcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AlbumSearchTest
{
    @Test
    public void searchForAlbumTest1()
    {
        AlbumQueryBuilder queryBuilder = new AlbumQueryBuilder.Builder().albumName("disarm the descent").setLimit(30).setPage(1).build();
        AlbumSearcher searcher = new AlbumSearcher();
        List<Album> albums = searcher.search(queryBuilder);
        System.out.println(albums);
    }

}
