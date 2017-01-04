package exp.rusan.musicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Songs recycler view adapter.
 * <!--
 * Author: Rusan
 * Date: 2017/1/3
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongViewHolder> {

    private SongClickListener clickListener;
    private List<SongBean> songSet;

    public interface SongClickListener {
        void onSongClick(int position);
    }

    public SongsAdapter(SongClickListener clickListener) {
        this.clickListener = clickListener;
        this.songSet = new ArrayList<SongBean>();
    }

    public SongBean getSong(int position) {
        return songSet.get(position);
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent,
                false);
        return new SongViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        SongBean song = songSet.get(position);
        holder.tvTitle.setText(song.getTitle());
        holder.tvSubTitle.setText(song.getArtist() + " - " + song.getAlbum());
        holder.tvDuration.setText(song.getDuration().toString());
    }

    @Override
    public int getItemCount() {
        return songSet.size();
    }

    
    static class SongViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivIndicator;
        public TextView tvTitle;
        public TextView tvSubTitle;
        public TextView tvDuration;
        public ImageButton ibMore;

        public SongViewHolder(View itemView, final SongClickListener clickListener) {
            super(itemView);

            ivIndicator = (ImageView) itemView.findViewById(R.id.iv_indicator);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvSubTitle = (TextView) itemView.findViewById(R.id.tv_subtitle);
            tvDuration = (TextView) itemView.findViewById(R.id.tv_duration);
            ibMore = (ImageButton) itemView.findViewById(R.id.ib_more);

            ibMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2017/1/3 Item more button processing.
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( clickListener != null ) {
                        clickListener.onSongClick(getAdapterPosition());
                    }
                }
            });
        }
    }


}
