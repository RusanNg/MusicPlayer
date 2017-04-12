package exp.rusan.musicplayer;

import java.util.List;

/**
 * Created by Rusan on 2017/4/12.
 */

public class DataTree<K, V> {

    private K groupItem;
    private List<V> subItems;

    public DataTree(K groupItem, List<V> subItems) {
        this.groupItem = groupItem;
        this.subItems = subItems;
    }

    public K getGroupItem() {
        return groupItem;
    }

    public List<V> getSubItems() {
        return subItems;
    }
}
