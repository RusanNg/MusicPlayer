package exp.rusan.musicplayer.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.HashMap;
import java.util.Map;

import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.Util.PermissionUtils;
import exp.rusan.musicplayer.presenter.AlbumsPresenter;
import exp.rusan.musicplayer.presenter.ArtistsPresenter;
import exp.rusan.musicplayer.presenter.TracksPresenter;
import exp.rusan.musicplayer.view.adapter.LibraryFragmentPagerAdapter;
import exp.rusan.musicplayer.view.fragment.AlbumsFragment;
import exp.rusan.musicplayer.view.fragment.ArtistsFragment;
import exp.rusan.musicplayer.view.fragment.TracksFragment;

public class TrackListActivity extends BaseActivity {

    private final static String TAG = TrackListActivity.class.getSimpleName();

    private Map<String, Fragment> fragmentMap;

    private TracksFragment tracksFragment;
    private ArtistsFragment artistsFragment;
    private AlbumsFragment albumsFragment;

    private TracksPresenter tracksPresenter;
    private ArtistsPresenter artistsPresenter;
    private AlbumsPresenter albumsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentLayout(R.layout.content_music_list, new AppBarLayout.ScrollingViewBehavior());

        fragmentMap = new HashMap<>();

        artistsFragment = ArtistsFragment.newInstance();
        tracksFragment = TracksFragment.newInstance();
        albumsFragment = AlbumsFragment.newInstance();

        fragmentMap.put("artists", artistsFragment);
        fragmentMap.put("tracks", tracksFragment);
        fragmentMap.put("albums", albumsFragment);


        // TODO: 2017/1/6 权限管理
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_READ_EXTERNAL_STORAGE,
                permissionGrant);
    }

    void setView() {


        albumsFragment.setPresenter(albumsPresenter);
        artistsFragment.setPresenter(artistsPresenter);
        tracksFragment.setPresenter(tracksPresenter);

        // TabLayout and ViewPager
        ViewPager vpLibrary = (ViewPager) findViewById(R.id.vp_library);

        LibraryFragmentPagerAdapter vpLibraryAdapter = new LibraryFragmentPagerAdapter
                (getSupportFragmentManager(), this);
        vpLibraryAdapter.setFragments(fragmentMap);
        vpLibrary.setAdapter(vpLibraryAdapter);

        TabLayout tlLibrary = (TabLayout) findViewById(R.id.tl_library);
        tlLibrary.setupWithViewPager(vpLibrary);
    }

    void setPresenter() {
        albumsPresenter = new AlbumsPresenter(getApplicationContext(),
                albumsFragment);
        tracksPresenter = new TracksPresenter(getApplicationContext(),
                tracksFragment);
        artistsPresenter = new ArtistsPresenter(getApplicationContext(),
                artistsFragment);
    }


    private PermissionUtils.PermissionGrant permissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {

                case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
                    Snackbar.make(findViewById(R.id.content_music_list), "Permission requested is " +
                            "granted!!!", Snackbar.LENGTH_SHORT).show();
                    setPresenter();
                    setView();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults,
                permissionGrant);
    }
}
