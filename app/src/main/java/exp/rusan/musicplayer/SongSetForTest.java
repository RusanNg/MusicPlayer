package exp.rusan.musicplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Song set for test
 * <!--
 * Author: Rusan
 * Date: 2017/1/4
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class SongSetForTest {

   static public List<SongBean> getSongSet() {

       List<SongBean> songSet = new ArrayList<>();

       for (int i = 1; i < 21; i++) {
           songSet.add(new SongBean("Song_" + i, "Artist_" + i, "Album_" + i, new Integer(i) ));
       }

       return songSet;
   }
}
