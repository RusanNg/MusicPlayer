package exp.rusan.musicplayer.TracksPage;

import java.util.List;

import exp.rusan.musicplayer.IBasePresenter;
import exp.rusan.musicplayer.IBaseView;
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

public interface ITracksPageContract {

    interface ITracksPageView extends IBaseView<ITracksPagePresenter> {

        void setPlayingIndicator(boolean isPlaying);

        void showTracks(List<Track> pTracks);

        void showTrackOptionsUi(Integer id);

        void showReloadTracks(List<Track> pTracks);

    }

    interface ITracksPagePresenter extends IBasePresenter {

        void loadTracks();

        void ReloadTracks(List<Track> pTracks);

    }
}
