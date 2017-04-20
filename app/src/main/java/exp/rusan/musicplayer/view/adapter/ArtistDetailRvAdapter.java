package exp.rusan.musicplayer.view.adapter;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.Util.SecondaryListAdapter;
import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.bean.Track;

/**
 * Created by Rusan on 2017/4/18.
 */

public class ArtistDetailRvAdapter extends SecondaryListAdapter<ArtistDetailRvAdapter
        .GroupItemViewHolder, ArtistDetailRvAdapter.SubItemViewHolder> {

    private Context context;

    private List<DataTree<Album, Track>> dataTrees;

    public ArtistDetailRvAdapter(Context context) {
        this.context = context;
    }

    public void setAtDataTrees(List pDataTrees) {
        this.dataTrees = pDataTrees;
        notifyNewData(pDataTrees);
    }


    @Override
    public RecyclerView.ViewHolder groupItemViewHolder(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_artist_detail_album, parent, false);

        return new GroupItemViewHolder(v);
    }

    @Override
    public RecyclerView.ViewHolder subItemViewHolder(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_artist_detail_track, parent, false);

        return new SubItemViewHolder(v);
    }

    @Override
    public void onGroupItemBindViewHolder(RecyclerView.ViewHolder holder, int groupItemIndex) {

        final GroupItemViewHolder groupItemVh = (GroupItemViewHolder) holder;
        DataTree<Album, Track> dt = dataTrees.get(groupItemIndex);

        if (dt.getGroupItem().getArtUri() != null) {
            Glide.with(context).load(dt.getGroupItem().getArtUri()).into(groupItemVh.ivAlbumArt);
        }

        groupItemVh.tvAlbumTitle.setText(dt.getGroupItem().getTitle());

        groupItemVh.tvNumTracks.setText(dt.getGroupItem().getNumTracks() + " tracks");

        groupItemVh.ivExpand.setImageDrawable(context.getResources().getDrawable(R.drawable
                .ic_shrink_white_24dp));

    }

    @Override
    public void onSubItemBindViewHolder(RecyclerView.ViewHolder holder, int groupItemIndex, int
            subItemIndex) {

        SubItemViewHolder subItemVh = (SubItemViewHolder) holder;

        DataTree<Album, Track> dt = dataTrees.get(groupItemIndex);

        subItemVh.tvTrackTitle.setText(dt.getSubItems().get(subItemIndex).getTitle());

        if (dt.getSubItems().get(subItemIndex).getDuration() >= 72000000) {

            subItemVh.tvTrackDuration.setText(new SimpleDateFormat("hh:mm:ss").format(new Date
                    (dt.getSubItems().get(subItemIndex).getDuration())));

        } else {

            subItemVh.tvTrackDuration.setText(new SimpleDateFormat("mm:ss").format(new Date(dt
                    .getSubItems().get(subItemIndex).getDuration())));

        }

    }

    @Override
    public void onGroupItemClick(Boolean isExpand, GroupItemViewHolder holder, int
            groupItemIndex) {

        ImageView ivExpand = holder.ivExpand;

        if (!isExpand) {

            ivExpand.setImageDrawable(context.getResources().getDrawable(R.drawable
                    .anim_expand));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((AnimatedVectorDrawable) ivExpand.getDrawable()).start();
            } else {
                ivExpand.setImageDrawable(context.getResources().getDrawable(R.drawable
                        .anim_shrink));
            }

        } else {

            ivExpand.setImageDrawable(context.getResources().getDrawable(R.drawable
                    .anim_shrink));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ( (AnimatedVectorDrawable)ivExpand.getDrawable() ).start();
            } else {
                ivExpand.setImageDrawable(context.getResources().getDrawable(R.drawable
                        .anim_expand));
            }

        }

    }

    @Override
    public void onSubItemClick(SubItemViewHolder holder, int subItemIndex) {
        Toast.makeText(context, holder.tvTrackTitle.getText(), Toast.LENGTH_SHORT).show();
    }


    public static class GroupItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAlbumArt;
        private TextView tvAlbumTitle;
        private TextView tvNumTracks;
        private ImageView ivExpand;

        public GroupItemViewHolder(View itemView) {
            super(itemView);
            ivAlbumArt = (ImageView) itemView.findViewById(R.id.iv_artist_detail_album_art);
            tvAlbumTitle = (TextView) itemView.findViewById(R.id.tv_artist_detail_album_title);
            tvNumTracks = (TextView) itemView.findViewById(R.id.tv_artist_detail_album_numtracks);
            ivExpand = (ImageView) itemView.findViewById(R.id.iv_artist_detail_album_extend);

            ivExpand.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable
                    .anim_expand));
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
}
