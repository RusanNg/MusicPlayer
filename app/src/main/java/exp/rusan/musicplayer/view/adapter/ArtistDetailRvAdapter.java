package exp.rusan.musicplayer.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import exp.rusan.musicplayer.DataTree;
import exp.rusan.musicplayer.ForTest;
import exp.rusan.musicplayer.R;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/3/23
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class ArtistDetailRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Boolean> groupItemStatus;

    private List<DataTree<String, String>> dataTrees;

    public ArtistDetailRvAdapter() {

        groupItemStatus = new ArrayList<>();

        dataTrees = ForTest.getDataTreeList();
        initGroupItemStatus(groupItemStatus);


    }

    private void initGroupItemStatus(List l) {
        for (int i = 0; i < dataTrees.size(); i++) {
            l.add(false);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == ItemStatus.VIEW_TYPE_GROUPITEM) {

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_artist_detail_album, parent, false);
            viewHolder = new GroupItemViewHolder(v);

        } else if (viewType == ItemStatus.VIEW_TYPE_SUBITEM) {

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_artist_detail_track, parent, false);
            viewHolder = new SubItemViewHolder(v);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final ItemStatus itemStatus = getItemStatusByPosition(position);

        final DataTree<String, String> dt = dataTrees.get(itemStatus.getGroupItemIndex());

        if ( itemStatus.getViewType() == ItemStatus.VIEW_TYPE_GROUPITEM ) {

            final GroupItemViewHolder groupItemVh = (GroupItemViewHolder) holder;

            groupItemVh.tvAlbumTitle.setText(dt.getGroupItem());

            groupItemVh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int groupItemIndex = itemStatus.getGroupItemIndex();

                    if ( !groupItemStatus.get(groupItemIndex) ) {

                        groupItemStatus.set(groupItemIndex, true);

                        notifyItemRangeInserted(groupItemIndex + 1, dt.getSubItems().size());

                    } else {

                        groupItemStatus.set(groupItemIndex, false);

                        notifyItemRangeRemoved(groupItemIndex + 1, dt.getSubItems().size());

                    }

                }
            });

        } else if (itemStatus.getViewType() == ItemStatus.VIEW_TYPE_SUBITEM) {

            SubItemViewHolder subItemVh = (SubItemViewHolder) holder;

            subItemVh.tvTrackTitle.setText(dt.getSubItems().get(itemStatus.getSubItemIndex()));

        }


    }

    @Override
    public int getItemCount() {

        int itemCount = 0;

        for (int i = 0; i < dataTrees.size(); i++) {

            if (groupItemStatus.get(i)) {
                itemCount += dataTrees.get(i).getSubItems().size() + 1;
            } else {
                itemCount++;
            }

        }

        return itemCount;
    }


    @Override
    public int getItemViewType(int position) {
        return getItemStatusByPosition(position).getViewType();
    }

    private ItemStatus getItemStatusByPosition(int position) {

        ItemStatus itemStatus = new ItemStatus();

        int count = 0;
        int i = 0;

        for ( i = 0; i < groupItemStatus.size(); i++ ) {

            if (count == position) {

                itemStatus.setViewType(ItemStatus.VIEW_TYPE_GROUPITEM);
                break;

            } else if (count > position) {

                itemStatus.setViewType(ItemStatus.VIEW_TYPE_SUBITEM);
                itemStatus.setSubItemIndex(position - ( count - dataTrees.get(i).getSubItems().size() ) - 1);
                break;

            }

            if (groupItemStatus.get(i)) {

                count += dataTrees.get(i).getSubItems().size() + 1;

            } else {

                count++;

            }


        }

        itemStatus.setGroupItemIndex(i);

        return itemStatus;
    }


    public static class GroupItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAlbumArt;
        private TextView tvAlbumTitle;
        private TextView tvNumTracks;

        public GroupItemViewHolder(View itemView) {
            super(itemView);
            ivAlbumArt = (ImageView) itemView.findViewById(R.id.iv_artist_detail_album_art);
            tvAlbumTitle = (TextView) itemView.findViewById(R.id.tv_artist_detail_album_title);
            tvNumTracks = (TextView) itemView.findViewById(R.id.tv_artist_detail_album_numtracks);
        }
    }

    public static class SubItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTrackTitle;
        private TextView tvTrackDuration;

        public SubItemViewHolder(View itemView) {
            super(itemView);
            tvTrackTitle = (TextView) itemView.findViewById(R.id.tv_artist_detail_track_title);
            tvTrackDuration = (TextView) itemView.findViewById(R.id.tv_artist_detail_track_duration);
        }
    }

    private static class ItemStatus {

        public static final int VIEW_TYPE_GROUPITEM = 0;
        public static final int VIEW_TYPE_SUBITEM = 1;

        private int viewType;
        private int groupItemIndex = 0;
        private int subItemIndex = -1;

        public ItemStatus() {
        }

        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }

        public int getGroupItemIndex() {
            return groupItemIndex;
        }

        public void setGroupItemIndex(int groupItemIndex) {
            this.groupItemIndex = groupItemIndex;
        }

        public int getSubItemIndex() {
            return subItemIndex;
        }

        public void setSubItemIndex(int subItemIndex) {
            this.subItemIndex = subItemIndex;
        }
    }



}
