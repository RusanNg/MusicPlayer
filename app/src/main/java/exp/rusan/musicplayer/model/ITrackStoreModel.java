package exp.rusan.musicplayer.model;

import java.util.List;

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
        void onChange(List pData);
    }

    void getTracks(LoadDataCallback pCallback);

    void getArtists(LoadDataCallback pCallback);

    void getAlbums(LoadDataCallback pCallback);

    void getArtistById(int pId, LoadDataCallback pCallback);

    void getAlbumById(int pId, LoadDataCallback pCallback);

    void getTrackById(int pId, LoadDataCallback pCallback);


}
