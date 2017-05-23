package exp.rusan.musicplayer.presenter;

import java.util.List;

import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.constract.IAlbumDetailConstract;
import exp.rusan.musicplayer.model.ITrackStoreModel;
import exp.rusan.musicplayer.model.TrackStore;

/**
 * Created by Rusan on 2017/5/23.
 */

public class AlbumDetailPresenter implements IAlbumDetailConstract.IAlbumDetailPresenter {

    private ITrackStoreModel model;
    private IAlbumDetailConstract.IAlbumDetailView view;
    private int albumId;

    public AlbumDetailPresenter(IAlbumDetailConstract.IAlbumDetailView pView) {
        this.model = TrackStore.getInstance();
        this.view = pView;
    }

    @Override
    public void start() {
        getAlbum(albumId);
        getTracks(albumId);
    }

    @Override
    public void setAlbumId(int pId) {
        this.albumId = pId;
    }

    @Override
    public void getAlbum(int pId) {
        model.getAlbumById(pId, new ITrackStoreModel.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object pData) {
                view.showAlbum((Album) pData);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void getTracks(int pAlbumId) {

        model.getTracksByAlbumId(pAlbumId, new ITrackStoreModel.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object pData) {
                view.showTracks((List) pData);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }
}
