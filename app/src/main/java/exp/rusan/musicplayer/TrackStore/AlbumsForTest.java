package exp.rusan.musicplayer.TrackStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: For test
 * <!--
 * Author: Rusan
 * Date: 2017/2/14
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class AlbumsForTest {

    private static List<Album> albums = new ArrayList<>();

    public AlbumsForTest() {


    }

    public static List getAlbums() {

        albums.add(new Album(0, "第零张专辑", "零", 10, "./storage/emulated/0/Android/data/com.android" +
                ".providers.media/albumthumbs/1483422811703"));
        albums.add(new Album(1, "第一张专辑", "一", 11, "./storage/emulated/0/Android/data/com.android" +
                ".providers.media/albumthumbs/1483423301721"));
        albums.add(new Album(2, "第二张专辑", "二", 12, "./storage/emulated/0/Android/data/com.android" +
                ".providers.media/albumthumbs/1483423510850"));
        albums.add(new Album(3, "第三张专辑", "三", 13, "./storage/emulated/0/Android/data/com.android" +
                ".providers.media/albumthumbs/1483422821368"));
        albums.add(new Album(4, "第四张专辑", "四", 14, "./storage/emulated/0/Android/data/com.android" +
                ".providers.media/albumthumbs/1483423287978"));

        return albums;
    }
}
