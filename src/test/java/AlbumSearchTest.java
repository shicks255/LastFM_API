import com.steven.hicks.beans.Album;
import com.steven.hicks.logic.AlbumQueryBuilder;
import com.steven.hicks.logic.AlbumSearcher;
import org.junit.Test;
import java.util.List;

public class AlbumSearchTest
{
    @Test
    public void searchForAlbumTest1()
    {
        AlbumQueryBuilder queryBuilder = new AlbumQueryBuilder.Builder().albumName("disarm the descent").setLimit(1).setPage(1).build();
        AlbumSearcher searcher = new AlbumSearcher();
        List<Album> albums = searcher.search(queryBuilder);

        Album full = searcher.getFullAlbum(albums.get(0).getMbid());

        System.out.println(full);
    }

}
