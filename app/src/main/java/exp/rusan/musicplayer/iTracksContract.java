package exp.rusan.musicplayer;

import java.util.List;

import exp.rusan.musicplayer.TrackStore.Track;

/**
 * Description: Tracks MVP 订约
 * <!--
 * Author: Rusan
 * Date: 2017/1/11
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public interface ITracksContract {

    interface IView extends IBaseView<IPresenter> {

        void setPlayingIndicator(boolean isPlaying);

        void showTracks(List<Track> pTracks);

        void showTrackOptionsUi(Integer id);

        void showReloadTracks(List<Track> pTracks);

    }

    interface IPresenter extends IBasePresenter {

        void loadTracks();

        void ReloadTracks(List<Track> pTracks);

    }
}
