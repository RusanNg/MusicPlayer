package exp.rusan.musicplayer.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exp.rusan.musicplayer.DataTree;
import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.bean.Track;

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

    private List<DataTree<Album, Track>> dataTrees;

    private Context context;

    public ArtistDetailRvAdapter(Context pContext) {

        this.context = pContext;
        dataTrees = new ArrayList<>();
        groupItemStatus = new ArrayList<>();

        initGroupItemStatus(groupItemStatus);

    }

    public void setDataTrees(List<DataTree<Album, Track>> dt) {
//        Logger.i(dt.size() + " ");
        this.dataTrees = dt;
        initGroupItemStatus(groupItemStatus);
        notifyDataSetChanged();
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

        Logger.i(itemStatus.getGroupItemIndex() + " ");

        final DataTree<Album, Track> dt = dataTrees.get(itemStatus.getGroupItemIndex());

        if ( itemStatus.getViewType() == ItemStatus.VIEW_TYPE_GROUPITEM ) {

            final GroupItemViewHolder groupItemVh = (GroupItemViewHolder) holder;

            if (dt.getGroupItem().getArtUri() != null) {
                Glide.with(context).load(dt.getGroupItem().getArtUri()).into(groupItemVh.ivAlbumArt);
            }

            groupItemVh.tvAlbumTitle.setText(dt.getGroupItem().getTitle());

            groupItemVh.tvNumTracks.setText(dt.getGroupItem().getNumTracks() + "tracks");

            groupItemVh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int groupItemIndex = itemStatus.getGroupItemIndex();

                    if ( !groupItemStatus.get(groupItemIndex) ) {

                        groupItemStatus.set(groupItemIndex, true);

                        notifyItemRangeInserted(groupItemVh.getAdapterPosition() + 1, dt.getSubItems
                                ().size());

                    } else {

                        groupItemStatus.set(groupItemIndex, false);

                        notifyItemRangeRemoved(groupItemVh.getAdapterPosition() + 1, dt.getSubItems().size());

                    }

                }
            });

        } else if (itemStatus.getViewType() == ItemStatus.VIEW_TYPE_SUBITEM) {

            SubItemViewHolder subItemVh = (SubItemViewHolder) holder;

            subItemVh.tvTrackTitle.setText(dt.getSubItems().get(itemStatus.getSubItemIndex()).getTitle());

            if (dt.getSubItems().get(itemStatus.getSubItemIndex()).getDuration() >= 72000000) {

                subItemVh.tvTrackDuration.setText(new SimpleDateFormat("hh:mm:ss").format(new Date
                        (dt.getSubItems().get(itemStatus.getSubItemIndex()).getDuration())));

            } else {

                subItemVh.tvTrackDuration.setText(new SimpleDateFormat("mm:ss").format(new Date(dt.getSubItems().get(itemStatus.getSubItemIndex()).getDuration())));

            }

        }


    }

    @Override
    public int getItemCount() {

        int itemCount = 0;

        if (groupItemStatus.size() == 0) {
            return 0;
        }

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

        for (i = 0; i < groupItemStatus.size(); i++ ) {

            if (count == position) {

                itemStatus.setViewType(ItemStatus.VIEW_TYPE_GROUPITEM);
                break;

            } else if (count > position) {

                itemStatus.setViewType(ItemStatus.VIEW_TYPE_SUBITEM);
                itemStatus.setSubItemIndex(position - ( count - dataTrees.get(i).getSubItems().size() ) );
                break;

            }

            if (groupItemStatus.get(i)) {

                count += dataTrees.get(i).getSubItems().size() + 1;

            } else {

                count++;

            }

        }

        if (i >= groupItemStatus.size()) {
            itemStatus.setGroupItemIndex(i - 1);
            itemStatus.setViewType(ItemStatus.VIEW_TYPE_SUBITEM);
            itemStatus.setSubItemIndex(position - ( count - dataTrees.get(i - 1).getSubItems().size
                    () ) );
        } else {
            itemStatus.setGroupItemIndex(i);
        }

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
