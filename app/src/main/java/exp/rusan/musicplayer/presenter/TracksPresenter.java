package exp.rusan.musicplayer.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.util.List;

import exp.rusan.musicplayer.bean.Track;
import exp.rusan.musicplayer.constract.ITracksPageContract;
import exp.rusan.musicplayer.model.ITrackStoreModel;
import exp.rusan.musicplayer.model.TrackStore;
import exp.rusan.musicplayer.model.contentLoader.TracksLoader;

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

public class TracksPresenter implements ITracksPageContract.ITracksPagePresenter {

    private static final String TAG = "TracksPresent";

    private ITrackStoreModel model;

    private final ITracksPageContract.ITracksPageView view;

    private Context context;

    private TracksLoader tracksLoader;


    public TracksPresenter(Context pContext, ITracksPageContract.ITracksPageView
            view) {
        this.context = pContext.getApplicationContext();
        this.tracksLoader = new TracksLoader(context, listener);
        this.model = TrackStore.getInstance();
        this.view = view;

//        view.setPresenter(this);

    }


    private ITrackStoreModel.OnDataChangeListener listener = new ITrackStoreModel.OnDataChangeListener() {
        @Override
        public void onChange(List pData) {
            reloadTracks((List<Track>) pData);
        }
    };


    @Override
    public void start() {
        loadTracks();
    }

    @Override
    public void loadTracks() {
        model.getTracks(new ITrackStoreModel.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object pData) {
                view.showTracks((List<Track>) pData);
            }

            @Override
            public void onDataNotAvailable() {
                Logger.i("onDataNotAvailable!!!");
            }
        });
    }

    @Override
    public void reloadTracks(List<Track> pTracks) {
        Logger.i("Reload Tracks!!!");

        model.getTracks(new ITrackStoreModel.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object pData) {
                view.showReloadTracks((List<Track>) pData);
            }

            @Override
            public void onDataNotAvailable() {
                Logger.i("onDataNotAvailable!!!");
            }
        });
    }

}
