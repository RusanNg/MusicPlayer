package exp.rusan.musicplayer.TracksPage;

import android.content.Context;

import java.util.List;

import exp.rusan.musicplayer.TrackStore.ITracksModel;
import exp.rusan.musicplayer.TrackStore.Track;
import exp.rusan.musicplayer.TrackStore.TracksLoader;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/1/12
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class TracksPagePresenter implements ITracksPageContract.ITracksPagePresenter {

    private static final String TAG = "TracksPresent";

    private final ITracksModel tracksLoader;

    private final ITracksPageContract.ITracksPageView trackView;

    private Context context;


    public TracksPagePresenter(Context pContext, ITracksPageContract.ITracksPageView
            trackView) {
        this.context = pContext;
        this.tracksLoader = TracksLoader.getInstance(pContext.getContentResolver(), listener);
        this.trackView = trackView;

//        trackView.setPresenter(this);

    }

    private ITracksModel.OnTracksChangeListener listener = new ITracksModel.OnTracksChangeListener() {
        @Override
        public void onChange(List<Track> pTracks) {
            ReloadTracks(pTracks);
        }
    };

    @Override
    public void loadTracks() {
        tracksLoader.getTasks(new ITracksModel.LoadTracksCallback() {
            @Override
            public void onTracksLoaded(List<Track> pTracks) {

                trackView.showTracks(pTracks);
            }

            @Override
            public void onDataNotAvailable() {
                // TODO: 2017/1/13 getTracks ERR
            }
        });
    }

    @Override
    public void ReloadTracks(List<Track> pTracks) {
        trackView.showReloadTracks(pTracks);
    }

    @Override
    public void start() {
        loadTracks();
    }
}
