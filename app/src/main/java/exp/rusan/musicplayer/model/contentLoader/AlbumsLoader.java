package exp.rusan.musicplayer.model.contentLoader;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.model.ITrackStoreModel;
import exp.rusan.musicplayer.model.TrackStore;

/**
 * Description:Load albums data via content provider
 * <!--
 * Author: Rusan
 * Date: 2017/2/22
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class AlbumsLoader {

    private static final String TAG = "AlbumsLoader";

    private Context context;

    private AlbumsContentObserver albumsContentObserver;

    private List<Album> albums;

    private Uri albumsUrl = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

    private String[] projection = {

            MediaStore.Audio.Albums._ID,
            MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Albums.NUMBER_OF_SONGS,
            MediaStore.Audio.Albums.ALBUM_ART,
            MediaStore.Audio.Albums.ARTIST

    };

    private String selection = "numsongs >= 1 ";

    private String sortOrder = MediaStore.Audio.Albums.ALBUM;

    public AlbumsLoader(Context pContext, ITrackStoreModel.OnDataChangeListener pListener) {
        this.context = pContext.getApplicationContext();

        albumsContentObserver = new AlbumsContentObserver(pListener);
        registerAlbumsContentObserver();

        loader();
    }


    /**
     * Register content observer.
     */
    private void registerAlbumsContentObserver() {
        context.getContentResolver().registerContentObserver(albumsUrl, false, albumsContentObserver);
    }

    private void loader() {
        albums = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(albumsUrl, projection, selection, null,
                sortOrder);

        if (cursor == null) {
            Log.i(TAG, "loader: cursor == null");
        } else if (!cursor.moveToFirst()) {
            Log.i(TAG, "loader: cursor is empty.");
        } else {

            int idCol = cursor.getColumnIndex(MediaStore.Audio.Albums._ID);
            int titleCol = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
            int artistIdCol = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID);
            int numTracksCol = cursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS);
            int artUriCol = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
            int artistCol = cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST);

            do {
                int id = cursor.getInt(idCol);
                String title = cursor.getString(titleCol);
                int artistId = cursor.getInt(artistIdCol);
                int numTracks = cursor.getInt(numTracksCol);
                String artUri = cursor.getString(artUriCol);
                String artist = cursor.getString(artistCol);

                TrackStore.getInstance().addAlbum(new Album.Builder(id, title).numTracks
                        (numTracks).artistTitle(artist).artUri(artUri).artistId(artistId).build());

            } while (cursor.moveToNext());

        }

        cursor.close();

    }


    /**
     * AlbumsContentObserver is to listen to albums data, when albums data changes, the method
     * onChanges will be invoked.
     */
    private class AlbumsContentObserver extends ContentObserver {

        private ITrackStoreModel.OnDataChangeListener listener;

        public AlbumsContentObserver(ITrackStoreModel.OnDataChangeListener pListener) {
            super(new Handler());
            this.listener = pListener;
        }


        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);

            Log.i(TAG, "onChange: Albums data chenged!!!");

            loader();
            listener.onChange(albums);
        }
    }


}
