package exp.rusan.musicplayer.AlbumsPage;

import android.content.Context;
import android.util.Log;

import java.util.List;

import exp.rusan.musicplayer.TrackStore.Album;
import exp.rusan.musicplayer.TrackStore.AlbumsLoader;
import exp.rusan.musicplayer.TrackStore.IAlbumsModel;

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

public class AlbumsPagePresenter implements IAlbumsContract.IAblumsPagePresenter {

    private static final String TAG = "AlbumsPagePresenter";

    private IAlbumsModel albumsModel;
    private IAlbumsContract.IAblumsPageView albumsView;
    private Context context;


    public AlbumsPagePresenter(Context pContext, IAlbumsContract.IAblumsPageView pAlbumsView) {
        this.context = pContext;
        this.albumsModel = AlbumsLoader.getInstance(pContext.getContentResolver(), listener);
        albumsView = pAlbumsView;
    }

    IAlbumsModel.OnAlbumsChangeListener listener = new IAlbumsModel.OnAlbumsChangeListener() {
        @Override
        public void onChange(List<Album> pAlbums) {
            reloadAlbums(pAlbums);
        }
    };


    @Override
    public void loadAlbums() {
        albumsModel.getAlbums(new IAlbumsModel.LoadAlbumsCallback() {
            @Override
            public void onAlbumsLoaded(List<Album> pAlbums) {
                albumsView.showAlbums(pAlbums);
            }

            @Override
            public void onDataNotAvailable() {
                Log.i(TAG, "onDataNotAvailable: albums data not available.");
            }
        });
    }

    @Override
    public void reloadAlbums(List<Album> pAlbums) {
        albumsView.showReloadAlbums(pAlbums);
    }

    @Override
    public void start() {
        loadAlbums();
    }
}
