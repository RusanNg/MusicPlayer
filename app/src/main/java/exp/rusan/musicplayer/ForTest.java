package exp.rusan.musicplayer;

import java.util.ArrayList;
import java.util.List;

import exp.rusan.musicplayer.Util.SecondaryListAdapter;

/**
 * Created by Rusan on 2017/4/12.
 */

public class ForTest {

    public static List<SecondaryListAdapter.DataTree<String,String>> getDataTreeList() {

        List<SecondaryListAdapter.DataTree<String, String>> l = new ArrayList<>();

        for ( int i = 0; i < 20; i++) {
            l.add(new SecondaryListAdapter.DataTree<>(getGroupList().get(i), getSubItemList() ) );
        }

        return l;

    }

    public static List getGroupList() {

        List<String> l = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            l.add("Group " + i);

        }

        return l;

    }

    public static List getSubItemList() {

        List<String> l = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            l.add("SubItem " + i);

        }

        return l;

    }


}
