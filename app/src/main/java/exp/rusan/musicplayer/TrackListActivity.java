package exp.rusan.musicplayer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.Map;

import exp.rusan.musicplayer.presenter.AlbumsPresenter;
import exp.rusan.musicplayer.presenter.ArtistsPresenter;
import exp.rusan.musicplayer.view.AlbumsFragment;
import exp.rusan.musicplayer.view.ArtistsFragment;
import exp.rusan.musicplayer.view.TracksFragment;
import exp.rusan.musicplayer.presenter.TracksPresenter;
import exp.rusan.musicplayer.Util.PermissionUtils;

public class TrackListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        setContentView(R.layout.activity_music_list);

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
                    Snackbar.make(findViewById(R.id.toolbar), "Permission requested is " +
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.music_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
