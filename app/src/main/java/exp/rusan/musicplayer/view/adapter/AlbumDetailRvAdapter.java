package exp.rusan.musicplayer.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import exp.rusan.musicplayer.Constant;
import exp.rusan.musicplayer.OnItemClickListener;
import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.bean.Track;

/**
 * Created by Rusan on 2017/5/23.
 */

public class AlbumDetailRvAdapter extends RecyclerView.Adapter<AlbumDetailRvAdapter.AlbumDetailVh> {

    private final String TAG = this.getClass().getSimpleName();

    private OnItemClickListener itemClickListener;
    private List<Track> tracks;

    public AlbumDetailRvAdapter(OnItemClickListener pItemClickListener) {
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
    public AlbumDetailRvAdapter.AlbumDetailVh onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist_detail_track, parent,
                false);
        return new AlbumDetailRvAdapter.AlbumDetailVh (view);
    }


    @Override
    public void onBindViewHolder(AlbumDetailRvAdapter.AlbumDetailVh holder, int position) {
        Track track = tracks.get(position);

        holder.tvTitle.setText(track.getTitle());

        holder.tvDuration.setText(Constant.durationToString(track.getDuration()));
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }


    static class AlbumDetailVh extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvDuration;
        public ImageButton ibMore;

        public AlbumDetailVh(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_artist_detail_track_title);
            tvDuration = (TextView) itemView.findViewById(R.id.tv_artist_detail_track_duration);
            ibMore = (ImageButton) itemView.findViewById(R.id.ib_artist_detail_track_options);

        }
    }

}
