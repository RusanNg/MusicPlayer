package exp.rusan.musicplayer.model;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/3/14
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public interface ITrackStoreModel {

    interface LoadDataCallback {

        void onDataLoaded(Object pData);

        void onDataNotAvailable();

    }

    interface OnDataChangeListener {
        void onChange();
    }

    void getTracks(LoadDataCallback pCallback);

    void getArtists(LoadDataCallback pCallback);

    void getAlbums(LoadDataCallback pCallback);

    void getArtistById(int pId, LoadDataCallback pCallback);

    void getAlbumById(int pId, LoadDataCallback pCallback);

    void getTrackById(int pId, LoadDataCallback pCallback);

    void getAlbumsByArtistId(int pId, LoadDataCallback pCallback);

    void getTracksByAlbumId(int pId, LoadDataCallback pCallback);

}
