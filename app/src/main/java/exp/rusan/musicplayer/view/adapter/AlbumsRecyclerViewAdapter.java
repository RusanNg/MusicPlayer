package exp.rusan.musicplayer.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import exp.rusan.musicplayer.bean.Album;

/**
 * Description: Recycler Adapter for Albums RecyclerView in AlbumsFragment
 * <!--
 * Author: Rusan
 * Date: 2017/2/14
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class AlbumsRecyclerViewAdapter extends RecyclerView.Adapter<AlbumsRecyclerViewAdapter.AlbumViewHolder> {

    private Context context;
    private OnItemClickListener onItemClickListener;
    private List<Album> albums;

    public AlbumsRecyclerViewAdapter(Context pContext, OnItemClickListener pOnItemClickListener) {

        this.onItemClickListener = pOnItemClickListener;

        albums = new ArrayList<>();
        this.context = pContext;
    }

    public void setAlbums(List<Album> pAlbums) {
        this.albums = pAlbums;
        notifyDataSetChanged();
    }

    public Album getAlbumByPosition(int position) {
        return albums.get(position);
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_albums, parent, false);

        return new AlbumViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {

        Album album = albums.get(position);
        Integer numTracks = album.getNumTracks();

        holder.tvAlbumTitle.setText(album.getTitle());

        if (numTracks > 1) {
            holder.tvAlbumSubtitle.setText(album.getArtistTitle() + " - " + numTracks + " tracks");
        } else {
            holder.tvAlbumSubtitle.setText(album.getArtistTitle() + " - " + numTracks + " track");
        }

        holder.ivAlbumArt.setImageDrawable(context.getResources().getDrawable(R.drawable
                .ic_adjust_black_24dp));
        if (album.getArtUri() != null) {
            Glide.with(context).load(album.getArtUri()).into(holder.ivAlbumArt);
        }

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }


    static class AlbumViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivAlbumArt;
        public TextView tvAlbumTitle;
        public TextView tvAlbumSubtitle;

        public AlbumViewHolder(View itemView, final OnItemClickListener pOnItemClickListener) {
            super(itemView);

            ivAlbumArt = (ImageView) itemView.findViewById(R.id.iv_item_albums_art);
            tvAlbumTitle = (TextView) itemView.findViewById(R.id.tv_item_albums_title);
            tvAlbumSubtitle = (TextView) itemView.findViewById(R.id.tv_item_albums_subtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pOnItemClickListener != null) {
                        pOnItemClickListener.onItemClick(getAdapterPosition(), v);
                    }
                }
            });


        }

    }
}

