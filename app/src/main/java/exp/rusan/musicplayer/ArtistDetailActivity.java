package exp.rusan.musicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

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

public class ArtistDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

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

    private boolean isHideToolbarView = false;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.frag_artist_detail);

        Logger.init().methodCount(0).hideThreadInfo();

        ButterKnife.bind(this);

        setSupportActionBar(tb);

        ctl.setTitle(" ");

        hvToolbar.bindTo("Some Title", "Subtitle");
        hvCtb.bindTo("Some Title", "Subtitle");

        abl.addOnOffsetChangedListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


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
    }
}
