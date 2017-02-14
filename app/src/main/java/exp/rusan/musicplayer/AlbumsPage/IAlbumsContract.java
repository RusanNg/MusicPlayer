package exp.rusan.musicplayer.AlbumsPage;


import exp.rusan.musicplayer.IBasePresenter;
import exp.rusan.musicplayer.IBaseView;

/**
 * Description: IAlbumsContract to constracting MVP architecture in Albums page
 * <!--
 * Author: Rusan
 * Date: 2017/2/14
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public interface IAlbumsContract {

    interface IAblumsPageView extends IBaseView<IAblumsPagePresenter> {

        void showAlbums();

    }

    interface IAblumsPagePresenter extends IBasePresenter {

        void loadAlums();

    }


}
