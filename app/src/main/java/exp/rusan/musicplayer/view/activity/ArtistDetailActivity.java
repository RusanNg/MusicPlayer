package exp.rusan.musicplayer.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import exp.rusan.musicplayer.Constant;
import exp.rusan.musicplayer.Util.SecondaryListAdapter;
import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.bean.Artist;
import exp.rusan.musicplayer.bean.Track;
import exp.rusan.musicplayer.constract.IArtistDetailConstract;
import exp.rusan.musicplayer.model.TrackStore;
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

        int artistId = getIntent().getIntExtra(Constant.ARTIST_ID, 0);
        artist = TrackStore.getInstance().getArtistById(artistId);

        super.onCreate(savedInstanceState);


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
    protected void onResume() {
        super.onResume();
        presenter.start();

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
