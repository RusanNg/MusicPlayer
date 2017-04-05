package exp.rusan.musicplayer.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.util.List;

import exp.rusan.musicplayer.constract.IArtistsPageContract;
import exp.rusan.musicplayer.bean.Artist;
import exp.rusan.musicplayer.model.ITrackStoreModel;
import exp.rusan.musicplayer.model.TrackStore;
import exp.rusan.musicplayer.model.contentLoader.ArtistsLoader;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/2/28
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class ArtistsPresenter implements IArtistsPageContract.IArtistsPresenter {

    private static final String TAG = "ArtistsPresenter";

    private ITrackStoreModel model;

    private ArtistsLoader artistsLoader;

    private IArtistsPageContract.IArtistsView view;


    public ArtistsPresenter(Context pContext, IArtistsPageContract.IArtistsView
            pView) {

        this.artistsLoader = new ArtistsLoader(pContext, listener);
        this.model = TrackStore.getInstance();
        this.view = pView;


    }

   private ITrackStoreModel.OnDataChangeListener listener = new ITrackStoreModel
           .OnDataChangeListener
           () {
        @Override
        public void onChange(List pData) {
            reloadArtists(pData);
        }
    };


    @Override
    public void loadArtists() {

        model.getArtists(new ITrackStoreModel.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object pData) {
                view.showArtists((List<Artist>) pData);
            }

            @Override
            public void onDataNotAvailable() {
                Logger.i("Artists is null");
            }
        });

    }

    @Override
    public void reloadArtists(List<Artist> pArtists) {
        model.getArtists(new ITrackStoreModel.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object pData) {
                view.showReloadArtists((List<Artist>) pData);
            }

            @Override
            public void onDataNotAvailable() {
                Logger.i("Artists is null");
            }
        });
    }

    @Override
    public void start() {
        loadArtists();
    }
}
