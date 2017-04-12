package exp.rusan.musicplayer.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import exp.rusan.musicplayer.Constant;
import exp.rusan.musicplayer.HeaderView;
import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.bean.Artist;
import exp.rusan.musicplayer.constract.IArtistDetailConstract;
import exp.rusan.musicplayer.presenter.ArtistDetailPresenter;

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

public class ArtistDetailActivity extends AppCompatActivity implements AppBarLayout
        .OnOffsetChangedListener, IArtistDetailConstract.IArtistDetailView {

    private boolean isHideToolbarView = false;

    private Artist artist;

    private IArtistDetailConstract.IArtistDetailPresenter presenter;

    @BindView(R.id.abl_artist_detail)
    AppBarLayout abl;

    @BindView(R.id.ctl_artist_detail)
    CollapsingToolbarLayout ctl;

    @BindView(R.id.tb_artist_detail)
    Toolbar tb;

    @BindView(R.id.hv_artist_detail_toolbar)
    HeaderView hvToolbar;

    @BindView(R.id.hv_artist_detail_ctl)
    HeaderView hvCtb;

    @BindView(R.id.rv_artist_detail_list)
    RecyclerView rvList;

    @BindView(R.id.iv_artist_detail_artist_art)
    ImageView ivArtistArt;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_artist_detail);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Constant.setTranslucentStatus(this);
        }

        ButterKnife.bind(this);

        int artistId = getIntent().getIntExtra(Constant.ARTIST_ID, 0);

        presenter = new ArtistDetailPresenter(this);

        presenter.setArtistId(artistId);

        setSupportActionBar(tb);

        ctl.setTitle(" ");

        hvToolbar.setTitle("Some Title", "Subtitle");
        hvCtb.setTitle("Some Title", "Subtitle");

        abl.addOnOffsetChangedListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            hvToolbar.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;
        } else if (percentage < 1f && !isHideToolbarView) {
            hvToolbar.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (percentage >= 0.5) {
                ivArtistArt.setTransitionName(null);
            } else {
                ivArtistArt.setTransitionName("art_artists_to_detail");
             }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;

            default:
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(IArtistDetailConstract.IArtistDetailPresenter pPresenter) {
        this.presenter = pPresenter;

    }

    @Override
    public void showArtist(Artist pArtist) {
        Logger.i("here!!!");
        if (pArtist.getArtUri() != null) {
            Glide.with(getApplicationContext()).load(pArtist.getArtUri()).into(ivArtistArt);
        }

        hvCtb.setTitle(pArtist.getTitle());
        hvCtb.setSubtitle(Constant.numAlbumsToString(getApplication(), pArtist.getNumAlbums()
        ) + " - " + Constant.numTracksToString(getApplication(), pArtist.getNumTracks()));

        hvToolbar.setTitle(pArtist.getTitle());
        hvToolbar.setSubtitle(Constant.numAlbumsToString(getApplication(), pArtist.getNumAlbums()
        ) + " - " + Constant.numTracksToString(getApplication(), pArtist.getNumTracks()));


    }

    @Override
    public void showAlbums(List pAlbums) {

    }

    @Override
    public void showTracks(List pTracks) {

    }

    @Override
    public void hideTracks() {

    }
}
