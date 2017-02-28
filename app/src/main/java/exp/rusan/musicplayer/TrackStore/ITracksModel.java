package exp.rusan.musicplayer.TrackStore;

import java.util.List;

/**
 * Description: Track Model for MVP
 * <!--
 * Author: Rusan
 * Date: 2017/1/12
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public interface ITracksModel {

    interface LoadTracksCallback {

        void onTracksLoaded(List<Track> pTracks);

        void onDataNotAvailable();
    }

    interface GetTrackCallback {

        void onTrackLoaded(Track pTrack);

        void onDataNotAvailable();
    }

    interface OnTracksChangeListener {
        void onChange(List<Track> pTracks);
    }

    void getTasks(LoadTracksCallback pCallback);

    void getTrack(GetTrackCallback pCallback);

}
