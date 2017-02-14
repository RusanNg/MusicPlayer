package exp.rusan.musicplayer.TracksPage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.TrackStore.Track;

/**
 * Description: Tracks recycler view adapter.
 * <!--
 * Author: Rusan
 * Date: 2017/1/3
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class TracksRecyclerViewAdapter extends RecyclerView.Adapter<TracksRecyclerViewAdapter.TrackViewHolder> {

    private final String TAG = this.getClass().getSimpleName();

    private OnItemClickListener itemClickListener;
    private List<Track> tracks;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public TracksRecyclerViewAdapter(OnItemClickListener pItemClickListener) {
        this.itemClickListener = pItemClickListener;
        this.tracks = new ArrayList<>();
    }

    public Track getTrack(int position) {
        return tracks.get(position);
    }

    public void setTracks(List<Track> pTracks) {
        tracks = pTracks;
        notifyDataSetChanged();
    }


    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tracks, parent,
                false);
        return new TrackViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        Track track = tracks.get(position);
        Integer trackDuration = track.getDuration();
        holder.tvTitle.setText(track.getTitle());
        holder.tvSubTitle.setText(track.getArtist() + " - " + track.getAlbum());

        if (trackDuration >= 72000000) {

            holder.tvDuration.setText(new SimpleDateFormat("hh:mm:ss").format(new Date(trackDuration)));

        } else {

            holder.tvDuration.setText(new SimpleDateFormat("mm:ss").format(new Date(trackDuration)));

        }
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }


    static class TrackViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivIndicator;
        public TextView tvTitle;
        public TextView tvSubTitle;
        public TextView tvDuration;
        public ImageButton ibMore;

        public TrackViewHolder(View itemView, final OnItemClickListener pItemClickListener) {
            super(itemView);

            ivIndicator = (ImageView) itemView.findViewById(R.id.iv_indicator);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvSubTitle = (TextView) itemView.findViewById(R.id.tv_subtitle);
            tvDuration = (TextView) itemView.findViewById(R.id.tv_duration);
            ibMore = (ImageButton) itemView.findViewById(R.id.ib_options);

            ibMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("SongAdapter", "ibMore pressed!!!");
                    // TODO: 2017/1/3 Item more button processing.
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( pItemClickListener != null ) {
                        pItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }


}
