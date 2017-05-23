package exp.rusan.musicplayer.constract;

import java.util.List;

import exp.rusan.musicplayer.IBasePresenter;
import exp.rusan.musicplayer.IBaseView;
import exp.rusan.musicplayer.bean.Album;

/**
 * Created by Rusan on 2017/5/23.
 */

public interface IAlbumDetailConstract  {

    public interface IAlbumDetailPresenter extends IBasePresenter {

        void setAlbumId(int pId);

        void getAlbum(int pId);

        void getTracks(int pAlbumId);
    }

    public interface IAlbumDetailView extends IBaseView<IAlbumDetailConstract
            .IAlbumDetailPresenter> {

        void showAlbum(Album pAlbum);

        void showTracks(List pTracks);
    }


}
