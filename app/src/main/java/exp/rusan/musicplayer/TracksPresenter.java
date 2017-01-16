package exp.rusan.musicplayer;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import java.util.List;

import exp.rusan.musicplayer.TrackStore.ITrackModel;
import exp.rusan.musicplayer.TrackStore.Track;
import exp.rusan.musicplayer.TrackStore.TrackContentObserver;
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

public class TracksPresenter implements ITracksContract.IPresenter {

    private static final String TAG = "TracksPresent";

    private final TracksLoader tracksLoader;

    private final ITracksContract.IView trackView;

    private Context context;

    private TrackContentObserver trackContentObserver;

    public TracksPresenter(Context pContext, TracksLoader tracksLoader, ITracksContract.IView
            trackView) {
        this.context = pContext;
        this.tracksLoader = tracksLoader;
        this.trackView = trackView;

        trackView.setPresenter(this);

        trackContentObserver = new TrackContentObserver(pContext, handler);
        registerContentObserver();
    }


    private void registerContentObserver() {

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        context.getContentResolver().registerContentObserver(uri, false, trackContentObserver);

    }


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case PersistenceVariable.HANDLER_WHAT_TRACK_CONTENT_OBSERVER:

                    ReloadTracks((List<Track>) msg.obj);
                    break;

                default:
                    break;
            }

        }
    };


    @Override
    public void loadTracks() {
        tracksLoader.getTasks(new ITrackModel.LoadTracksCallback() {
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
