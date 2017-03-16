package exp.rusan.musicplayer.vIew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import exp.rusan.musicplayer.OnItemClickListener;
import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.bean.Artist;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/2/28
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class ArtistsRecyclerViewAdapter extends RecyclerView
        .Adapter<ArtistsRecyclerViewAdapter.ArtistViewHolder> {

    private static final String TAG = "ArtistsRvAdapter";

    private OnItemClickListener itemClicklistener;

    private List<Artist> artists;

    private Context context;


    public ArtistsRecyclerViewAdapter(Context pContext, OnItemClickListener pItemClicklistener) {
        this.context = pContext;
        this.itemClicklistener = pItemClicklistener;
        artists = new ArrayList<>();
    }


    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artists, parent,
                false);

        return new ArtistViewHolder(v, itemClicklistener);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {

        Log.i(TAG, "onBindViewHolder: " + artists.size());

        Artist artist = artists.get(position);
        Integer numAlbums = artist.getNumAlbums();
        Integer numTracks = artist.getNumTracks();

        String strSubtitleAlbum;
        String strSubtitleTrack;

        if (artist.getArtUri() != null) {

            Glide.with(context).load(artist.getArtUri()).into(holder.ivArt);
        }

        holder.tvTitle.setText(artist.getTitle());

        if (numAlbums > 1) {
            strSubtitleAlbum = " Albums";
        } else {
            strSubtitleAlbum = " Album";
        }

        if (numTracks > 1) {
            strSubtitleTrack = " Tracks";
        } else {
            strSubtitleTrack = " Track";
        }

        holder.tvSubtitle.setText(numAlbums + strSubtitleAlbum + " - " + numTracks + strSubtitleTrack);

    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public void setArtists(List<Artist> pArtists) {
        this.artists = pArtists;
        notifyDataSetChanged();
    }

    public Artist getArtist(int position) {
        return artists.get(position);
    }


    static class ArtistViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivArt;
        public TextView tvTitle;
        public TextView tvSubtitle;

        public ArtistViewHolder(View itemView, final OnItemClickListener pItemClickListener) {
            super(itemView);

            ivArt = (ImageView) itemView.findViewById(R.id.iv_item_artists_art);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_artists_title);
            tvSubtitle = (TextView) itemView.findViewById(R.id.tv_item_artists_subtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: artists itme onClick.");

                    if (pItemClickListener == null) {
                        Log.i(TAG, "onClick: pItemClickListener is null.");
                    } else {
                        pItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });

        }
    }

}
