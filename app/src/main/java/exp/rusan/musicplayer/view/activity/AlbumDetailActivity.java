package exp.rusan.musicplayer.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.List;

import exp.rusan.musicplayer.Constant;
import exp.rusan.musicplayer.OnItemClickListener;
import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.constract.IAlbumDetailConstract;
import exp.rusan.musicplayer.presenter.AlbumDetailPresenter;
import exp.rusan.musicplayer.view.adapter.AlbumDetailRvAdapter;
import exp.rusan.musicplayer.view.fragment.AlbumsFragment;

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

public class AlbumDetailActivity extends TwoTitleCollapsingToolbarActivity implements IAlbumDetailConstract.IAlbumDetailView {

    private Album album;

    private IAlbumDetailConstract.IAlbumDetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int albumId = getIntent().getIntExtra(Constant.ALBUM_ID, 0);

        presenter = new AlbumDetailPresenter(this);
        presenter.setAlbumId(albumId);

    }

    @Override
    protected String transitionName() {
        return AlbumsFragment.TRANSITION_NAME;
    }

    @Override
    protected String title() {
        return album.getTitle();
    }

    @Override
    protected String subTitle() {
        return album.getArtistTitle() + " - " + Constant.numTracksToString(getApplication(), album
                .getNumTracks());
    }

    @Override
    protected String artUri() {
        return album.getArtUri();
    }

    @Override
    protected RecyclerView.Adapter adapter() {
        return new AlbumDetailRvAdapter(onItemClickListener);
    }

    @Override
    public void updateAdapterData(List datas) {
        ((AlbumDetailRvAdapter)getAdapter()).setTracks(datas);
    }

    @Override
    protected void btnPlayAllOnClick(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
        setHeadView();
    }


    @Override
    public void setPresenter(IAlbumDetailConstract.IAlbumDetailPresenter pPresenter) {
        this.presenter = pPresenter;
    }

    @Override
    public void showAlbum(Album pAlbum) {
        this.album = pAlbum;
    }

    @Override
    public void showTracks(List pTracks) {
        updateAdapterData(pTracks);
    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position, View v) {
            Logger.i("Click.");
        }
    };
}
