package exp.rusan.musicplayer.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.util.List;

import exp.rusan.musicplayer.constract.IAlbumsPageContract;
import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.model.ITrackStoreModel;
import exp.rusan.musicplayer.model.TrackStore;
import exp.rusan.musicplayer.model.contentLoader.AlbumsLoader;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/2/21
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class AlbumsPresenter implements IAlbumsPageContract.IAblumsPagePresenter {

    private static final String TAG = "AlbumsPresenter";

    private ITrackStoreModel model;
    private IAlbumsPageContract.IAblumsPageView view;
    private Context context;
    private AlbumsLoader albumsLoader;


    public AlbumsPresenter(Context pContext, IAlbumsPageContract.IAblumsPageView pView) {
        this.context = pContext;
        this.albumsLoader = new AlbumsLoader(context.getApplicationContext(), listener);
        this.model = TrackStore.getInstance();
        this.view = pView;
    }


    ITrackStoreModel.OnDataChangeListener listener = new ITrackStoreModel.OnDataChangeListener() {
        @Override
        public void onChange(List pData) {
            reloadAlbums(pData);
        }
    };

    @Override
    public void loadAlbums() {
        model.getAlbums(new ITrackStoreModel.LoadDataCallback() {
            @Override
            public void onDataLoaded(List pData) {
                view.showAlbums(pData);
            }

            @Override
            public void onDataNotAvailable() {
                Logger.i("Albums is null.");
            }
        });
    }

    @Override
    public void reloadAlbums(List<Album> pAlbums) {
        model.getAlbums(new ITrackStoreModel.LoadDataCallback() {
            @Override
            public void onDataLoaded(List pData) {
                view.showReloadAlbums(pData);
            }

            @Override
            public void onDataNotAvailable() {
                Logger.i("Albums is null.");
            }
        });
    }

    @Override
    public void start() {
        loadAlbums();
    }
}
