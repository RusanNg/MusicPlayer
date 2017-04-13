package exp.rusan.musicplayer.presenter;

import java.util.ArrayList;
import java.util.List;

import exp.rusan.musicplayer.DataTree;
import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.bean.Artist;
import exp.rusan.musicplayer.bean.Track;
import exp.rusan.musicplayer.constract.IArtistDetailConstract;
import exp.rusan.musicplayer.model.ITrackStoreModel;
import exp.rusan.musicplayer.model.TrackStore;

/**
 * Created by Rusan on 2017/3/27.
 */

public class ArtistDetailPresenter implements IArtistDetailConstract.IArtistDetailPresenter {

    private ITrackStoreModel model;

    private IArtistDetailConstract.IArtistDetailView view;

    private int artistId;

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
        getDataTrees(artistId);
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

    public List getAlbums(int pArtistId) {

        final List<Album> albums = new ArrayList<>();

        model.getAlbumsByArtistId(pArtistId, new ITrackStoreModel.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object pData) {


                albums.clear();
                albums.addAll((List)pData);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return albums;

    }

    public List getTracks(int pAlbumId) {

        final List<Track> tracks = new ArrayList<>();

        model.getTracksByAlbumId(pAlbumId, new ITrackStoreModel.LoadDataCallback() {
            @Override
            public void onDataLoaded(Object pData) {
                tracks.clear();
                tracks.addAll((List)pData);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return tracks;

    }

    public List dataTreesBuilder(int pArtistId) {

        List<DataTree<Album, Track>> dataTrees = new ArrayList<>();

        List<Album> as = getAlbums(pArtistId);

//        Logger.i(getAlbums(pArtistId).size() + "");

        for (Album a : as){

            dataTrees.add(new DataTree<>(a, getTracks(a.getId())));

        }

        return dataTrees;
    }

    @Override
    public void getDataTrees(int pArtistId) {

//        Logger.i(dataTreesBuilder(pArtistId).size() + "");
        view.showDataTrees(dataTreesBuilder(pArtistId));
    }


}
