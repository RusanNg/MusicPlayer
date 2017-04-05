package exp.rusan.musicplayer.constract;

import java.util.List;

import exp.rusan.musicplayer.IBasePresenter;
import exp.rusan.musicplayer.IBaseView;
import exp.rusan.musicplayer.bean.Artist;

/**
 * Created by Rusan on 2017/3/27.
 */

public class IArtistDetailConstract {

    public interface IArtistDetailPresenter extends IBasePresenter{

        void setArtistId(int pArtistId);

        void getArtist(int pId);

        void getAlbums(int pArtistId);

        void getTracks(int pAlbumId);
    }

    public interface IArtistDetailView extends IBaseView<IArtistDetailPresenter> {

        void showArtist(Artist pArtist);

        void showAlbums(List pAlbums);

        void showTracks(List pTracks);

        void hideTracks();
    }

}
