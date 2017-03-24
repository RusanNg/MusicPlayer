package exp.rusan.musicplayer.constract;


import java.util.List;

import exp.rusan.musicplayer.IBasePresenter;
import exp.rusan.musicplayer.IBaseView;
import exp.rusan.musicplayer.bean.Album;

/**
 * Description: IAlbumsPageContract to constracting MVP architecture in Albums page
 * <!--
 * Author: Rusan
 * Date: 2017/2/14
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public interface IAlbumsPageContract {

    interface IAblumsPageView extends IBaseView<IAblumsPagePresenter> {

        void showAlbums(List<Album> pAlbums);

        void showReloadAlbums(List<Album> pAlbums);

    }

    interface IAblumsPagePresenter extends IBasePresenter {

        void loadAlbums();

        void reloadAlbums(List<Album> pAlbums);

    }


}