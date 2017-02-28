package exp.rusan.musicplayer.TrackStore;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

public class AlbumsLoader implements IAlbumsModel {

    private static final String TAG = "AlbumsLoader";

    private static AlbumsLoader albumsLoader;

    private ContentResolver contentResolver;

    private AlbumsContentObserver albumsContentObserver;

    private List<Album> albums;

    private Uri albumsUrl = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

    private String[] projection = {

        MediaStore.Audio.Albums._ID,
        MediaStore.Audio.Albums.ALBUM,
        MediaStore.Audio.Albums.ARTIST,
        MediaStore.Audio.Albums.NUMBER_OF_SONGS,
        MediaStore.Audio.Albums.ALBUM_ART

    };

    private String selection = "numsongs >= 1 ";

    private String sortOrder = MediaStore.Audio.Albums.ALBUM;

    private AlbumsLoader(ContentResolver pContentResolver, OnAlbumsChangeListener pListener ) {
        this.contentResolver = pContentResolver;

        albumsContentObserver = new AlbumsContentObserver(pListener);
        registerAlbumsContentObserver();

        loader();


    }


    public static AlbumsLoader getInstance(ContentResolver pContentResolver, OnAlbumsChangeListener
            pListener) {

        if (albumsLoader == null) {
            albumsLoader = new AlbumsLoader(pContentResolver, pListener);
        }

        return albumsLoader;

    }

    /**
     * Register content observer.
     */
    private void registerAlbumsContentObserver() {
        contentResolver.registerContentObserver(albumsUrl, false, albumsContentObserver);
    }

    private void loader() {
        albums = new ArrayList<>();

        Cursor cursor = contentResolver.query(albumsUrl, projection, selection, null,
                sortOrder);

        if (cursor == null) {
            Log.i(TAG, "loader: cursor == null");
        } else if (!cursor.moveToFirst()) {
            Log.i(TAG, "loader: cursor is empty.");
        } else {

            int idCol = cursor.getColumnIndex(MediaStore.Audio.Albums._ID);
            int albumCol = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
            int artistCol = cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
            int numSongsCol = cursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS);
            int artUriCol = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);

            do {
                int id = cursor.getInt(idCol);
                String album = cursor.getString(albumCol);
                String artist = cursor.getString(artistCol);
                Integer numSongs = cursor.getInt(numSongsCol);
                String artUri = cursor.getString(artUriCol);

                albums.add(new Album(id, album, artist, numSongs, artUri));


            } while (cursor.moveToNext());

        }

        cursor.close();

    }


    @Override
    public void getAlbums(LoadAlbumsCallback pCallback) {
        Log.i(TAG, "getAlbums: albums' s size : " + albums.size());

        if ( albums.isEmpty() ) {
            pCallback.onDataNotAvailable();
        } else {
            pCallback.onAlbumsLoaded(albums);
        }

    }

    @Override
    public void getAlbum(GetAlbumCallback pCallback) {

    }


    /**
     * AlbumsContentObserver is to listen to albums data, when albums data changes, the method
     * onChanges will be invoked.
     */
    private class AlbumsContentObserver extends ContentObserver {

        private OnAlbumsChangeListener listener;

        public AlbumsContentObserver(OnAlbumsChangeListener pListener) {
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
