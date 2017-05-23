package exp.rusan.musicplayer.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.List;

import exp.rusan.musicplayer.Constant;
import exp.rusan.musicplayer.Util.SecondaryListAdapter;
import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.bean.Artist;
import exp.rusan.musicplayer.bean.Track;
import exp.rusan.musicplayer.constract.IArtistDetailConstract;
import exp.rusan.musicplayer.presenter.ArtistDetailPresenter;
import exp.rusan.musicplayer.view.adapter.ArtistDetailRvAdapter;
import exp.rusan.musicplayer.view.fragment.ArtistsFragment;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/3/20
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class ArtistDetailActivity extends TwoTitleCollapsingToolbarActivity implements IArtistDetailConstract.IArtistDetailView {

    private Artist artist;

    private IArtistDetailConstract.IArtistDetailPresenter presenter;

    List<SecondaryListAdapter.DataTree<Album, Track>> dataTrees;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int artistId = getIntent().getIntExtra(Constant.ARTIST_ID, 0);
        presenter = new ArtistDetailPresenter(this);
        presenter.setArtistId(artistId);

    }

    @Override
    protected String transitionName() {
        return ArtistsFragment.TRANSITION_NAME;
    }

    @Override
    protected String title() {
        return artist.getTitle();
    }

    @Override
    protected String subTitle() {
        return Constant.numAlbumsToString(getApplication(), artist.getNumAlbums()
        ) + " - " + Constant.numTracksToString(getApplication(), artist.getNumTracks());
    }

    @Override
    protected String artUri() {
        return artist.getArtUri();
    }

    @Override
    protected RecyclerView.Adapter adapter() {
        return new ArtistDetailRvAdapter(this);
    }

    @Override
    public void updateAdapterData(List datas) {
        ((ArtistDetailRvAdapter)getAdapter()).setAtDataTrees(datas);
    }

    @Override
    protected void btnPlayAllOnClick(View view) {
        Logger.i("btn play all on click clicked");
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
        setHeadView();

    }

    @Override
    public void setPresenter(IArtistDetailConstract.IArtistDetailPresenter pPresenter) {
        this.presenter = pPresenter;

    }

    @Override
    public void showArtist(Artist pArtist) {
        this.artist = pArtist;
    }

    @Override
    public void showDataTrees(List<SecondaryListAdapter.DataTree<Album, Track>> pDataTrees) {
        updateAdapterData(pDataTrees);
    }


}
