package exp.rusan.musicplayer.presenter;

import exp.rusan.musicplayer.bean.Artist;
import exp.rusan.musicplayer.constract.IArtistDetailConstract;
import exp.rusan.musicplayer.model.ITrackStoreModel;
import exp.rusan.musicplayer.model.TrackStore;

/**
 * Created by Rusan on 2017/3/27.
 */

public class ArtistDetailPresenter implements IArtistDetailConstract.IArtistDetailPresenter {

    private ITrackStoreModel model;

    private IArtistDetailConstract.IArtistDetailView view;

    int artistId;


    public ArtistDetailPresenter(IArtistDetailConstract.IArtistDetailView pView) {
        this.model = TrackStore.getInstance();
        this.view = pView;
    }

    public void setArtistId(int pArtistId) {
        this.artistId = pArtistId;
    }

    @Override
    public void start() {
        getArtist(artistId);
    }

    @Override
    public void getArtist(int pId) {

        model.getArtistById(pId, new ITrackStoreModel.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object pData) {

                view.showArtist((Artist) pData);
            }

            @Override
            public void onDataNotAvailable() {
                com.orhanobut.logger.Logger.i("data note available.");
            }
        });
    }

    @Override
    public void getAlbums(int pArtistId) {

    }

    @Override
    public void getTracks(int pAlbumId) {

    }

}
