package exp.rusan.musicplayer.constract;

import java.util.List;

import exp.rusan.musicplayer.IBasePresenter;
import exp.rusan.musicplayer.IBaseView;
import exp.rusan.musicplayer.bean.Artist;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/2/28
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public interface IArtistsPageContract {

    interface IArtistsPresenter extends IBasePresenter {

        void loadArtists();

        void reloadArtists();

    }

    interface  IArtistsView extends IBaseView<IArtistsPresenter> {

        void showArtists(List<Artist> pArtists);

        void showReloadArtists(List<Artist> pArtists);
    }



}
