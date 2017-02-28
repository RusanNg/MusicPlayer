package exp.rusan.musicplayer.TrackStore;

import java.util.List;

/**
 * Description: Albums interface for MVP
 * <!--
 * Author: Rusan
 * Date: 2017/2/27
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public interface IAlbumsModel {

    interface LoadAlbumsCallback {

        void onAlbumsLoaded(List<Album> pAlbums);

        void onDataNotAvailable();

    }

    interface GetAlbumCallback {

        void onAlbumLoaded(Album pAlbum);

        void onDataNotAvalable();

     }

    interface OnAlbumsChangeListener {
        void onChange(List<Album> pAlbums);
    }

    void getAlbums(LoadAlbumsCallback pCallback);

    void getAlbum(GetAlbumCallback pCallback);


}
