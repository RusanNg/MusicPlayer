package exp.rusan.musicplayer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Audio.Media;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MusicListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = this.getClass().getSimpleName();
    public static final int whatMusicContentObserver = 1;

    private SongsAdapter songsAdapter;

    private MusicContentObserver musicContentObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

//        // TODO: 2017/1/6 权限管理
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//
//            int hasReadExternalStorage = checkSelfPermission("android.permission" +
//                    ".READ_EXTERNAL_STORAGE");
//            List<String> permissions = new ArrayList<>();
//            if (hasReadExternalStorage != PackageManager.PERMISSION_GRANTED) {
//                permissions.add("android.permission.READ_EXTERNAL_STORAGE");
//            } else {
//
//            }
//        }




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Song List
        RecyclerView rvSongs = (RecyclerView) findViewById(R.id.rv_songs);
        rvSongs.setHasFixedSize(true);
        rvSongs.setLayoutManager(new LinearLayoutManager(this));
        rvSongs.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        songsAdapter = new SongsAdapter(songClickListener);
        songsAdapter.setSongList(MusicLoader.getInstance(getContentResolver()).getSongList());
        rvSongs.setAdapter(songsAdapter);

        musicContentObserver = new MusicContentObserver(this, handler);

        registerContentObservers();

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.i(TAG, "-----handler-----");

            switch (msg.what) {
                case MusicListActivity.whatMusicContentObserver:
                    List<SongBean> songList = (ArrayList<SongBean>) msg.obj;
                    Log.i(TAG, "Song list from handler is " + songList.size());
                    songsAdapter.setSongList(songList);
                    break;

                default:
                    break;
            }
        }
    };

    private void registerContentObservers() {
        Uri musicUri = Media.EXTERNAL_CONTENT_URI;
        getContentResolver().registerContentObserver(musicUri, false, musicContentObserver);
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

    SongsAdapter.SongClickListener songClickListener = new SongsAdapter.SongClickListener() {
        @Override
        public void onSongClick(int position) {
            SongBean song = songsAdapter.getSong(position);
            Log.d(TAG, song.getTitle());

        }
    };

}
