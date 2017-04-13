package exp.rusan.musicplayer.constract;

import java.util.List;

import exp.rusan.musicplayer.DataTree;
import exp.rusan.musicplayer.IBasePresenter;
import exp.rusan.musicplayer.IBaseView;
import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.bean.Artist;
import exp.rusan.musicplayer.bean.Track;

/**
 * Created by Rusan on 2017/3/27.
 */

public class IArtistDetailConstract {

    public interface IArtistDetailPresenter extends IBasePresenter{

        void setArtistId(int pId);

        void getArtist(int pId);

//        void getAlbums(int pArtistId);
//
//        void getTracks(int pAlbumId);

        void getDataTrees(int pArtistId);
    }

    public interface IArtistDetailView extends IBaseView<IArtistDetailPresenter> {

        void showArtist(Artist pArtist);

        void showDataTrees(List<DataTree<Album, Track>> pDataTrees);
    }

}
