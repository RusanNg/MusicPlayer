package exp.rusan.musicplayer.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import exp.rusan.musicplayer.Constant;
import exp.rusan.musicplayer.HeaderView;
import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.RvTracksDividerItemDecoration;
import exp.rusan.musicplayer.view.adapter.ArtistDetailRvAdapter;

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

public abstract class TwoTitleCollapsingToolbarActivity extends AppCompatActivity implements AppBarLayout
        .OnOffsetChangedListener {

    private boolean isHideToolbarView = false;

    @BindView(R.id.abl_two_title)
    AppBarLayout abl;

    @BindView(R.id.ctl_two_title)
    CollapsingToolbarLayout ctl;

    @BindView(R.id.tb_two_title)
    Toolbar tb;

    @BindView(R.id.hv_two_title_toolbar)
    HeaderView hvToolbar;

    @BindView(R.id.hv_two_title_ctl)
    HeaderView hvCtb;

    @BindView(R.id.rv_two_title_list)
    RecyclerView rvList;

    @BindView(R.id.iv_two_title_art)
    ImageView ivArt;

    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acti_two_title_collapsing_toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Constant.setTranslucentStatus(this);
        }

        ButterKnife.bind(this);

        setSupportActionBar(tb);

        ctl.setTitle(" ");

        hvToolbar.setTitle("Some Title", "Subtitle");
        hvCtb.setTitle("Some Title", "Subtitle");

        setHeadView();

        abl.addOnOffsetChangedListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        rvList.setLayoutManager(new LinearLayoutManager(this));

        rvList.setHasFixedSize(true);
        rvList.addItemDecoration(new RvTracksDividerItemDecoration(this, LinearLayoutManager
                .VERTICAL));

        adapter = adapter();

        rvList.setAdapter(adapter);

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
                ivArt.setTransitionName(null);
            } else {
                ivArt.setTransitionName(transitionName());
            }
        }

    }

    protected abstract String transitionName();

    protected abstract String title();

    protected abstract String subTitle();

    protected abstract String artUri();

    protected abstract RecyclerView.Adapter adapter();

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void updateAdapterData(List datas) {
        ((ArtistDetailRvAdapter)getAdapter()).setAtDataTrees(datas);
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


    public void setHeadView() {
//        Logger.i("here!!!");
        if (artUri() != null && !artUri().isEmpty()) {
            Glide.with(getApplicationContext()).load(artUri()).into(ivArt);
        }

        hvCtb.setTitle(title());
        hvCtb.setSubtitle(subTitle());

        hvToolbar.setTitle(title());
        hvToolbar.setSubtitle(subTitle());

    }



}
