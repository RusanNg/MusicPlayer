package exp.rusan.musicplayer.TrackStore;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 使用CotentResolver(Content Provider)获取本地音乐库信息,并且监听数据变化
 * <!--
 * Author: Rusan
 * Date: 2017/1/6
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class TracksLoader implements ITrackModel{

    private static final String TAG = "TracksLoader";

    private List<Track> tracks;

    private static TracksLoader tracksLoader;

    private ContentResolver contentResolver;

    private TracksContentObserver tracksContentObserver;

    private Uri tracksUri = Media.EXTERNAL_CONTENT_URI;

    private String[] projection = {
            Media._ID,
            Media.TITLE,
            Media.ARTIST,
            Media.ALBUM,
            Media.DURATION
    };

    private String where = "mime_type in ('audio/mpeg', 'audio/x-ms-wma') and TITLE" +
            " <> 'audio' and is_music > 0";

    private String sortOrder = Media.DATA;


    private TracksLoader(ContentResolver pContentResolver, OnTracksChangeListener pListener) {
        this.contentResolver = pContentResolver;

        tracksContentObserver = new TracksContentObserver(pListener);
        registerTracksContentObserver();

        loader();
    }

    /**
     * Registe observer for tracks content resolver.
     */
    private void registerTracksContentObserver() {
        contentResolver.registerContentObserver(tracksUri, false, tracksContentObserver);
    }

    public static TracksLoader getInstance(ContentResolver pContentResolver, OnTracksChangeListener
            pListener) {
        if (tracksLoader == null) {
            tracksLoader = new TracksLoader(pContentResolver, pListener);
        }
        return tracksLoader;
    }

    private void loader() {
        tracks = new ArrayList<>();

        Cursor cursor = contentResolver.query(tracksUri, projection, where, null, sortOrder);
        if (cursor == null) {
            Log.v(TAG, "Music Loader cursor == null.");
        } else if (!cursor.moveToFirst()) {
            Log.v(TAG, "Music Loader cursor.moveToFirst return false.");
        } else {
            int idCol = cursor.getColumnIndex(Media._ID);
            int titleCol = cursor.getColumnIndex(Media.TITLE);
            int artistCol = cursor.getColumnIndex(Media.ARTIST);
            int albumCol = cursor.getColumnIndex(Media.ALBUM);
            int durationCol = cursor.getColumnIndex(Media.DURATION);

            do {
                int id = cursor.getInt(idCol);
                String title = cursor.getString(titleCol);
                String artist = cursor.getString(artistCol);
                String album = cursor.getString(albumCol);
                int duration = cursor.getInt(durationCol);

                tracks.add(new Track(id, title, artist, album, duration));

            } while (cursor.moveToNext());
        }

        cursor.close();
    }

    public List<Track> getReloadTracks() {
        loader();
        return tracks;
    }


    @Override
    public void getTasks(LoadTracksCallback pCallback) {
        Log.i(TAG, "Tracks's num is " + tracks.size());
        if (tracks.isEmpty()) {
            pCallback.onDataNotAvailable();
        } else {
            pCallback.onTracksLoaded(tracks);
        }
    }

    @Override
    public void getTrack(GetTrackCallback pCallback) {

    }


    /**
     * TrackCotentObserver to listen tracks data changes. The method onChange will be invoked
     * when data change.
     */
    private class TracksContentObserver extends ContentObserver{

        private OnTracksChangeListener listener;

        public TracksContentObserver(OnTracksChangeListener pListener) {
            super(new Handler());
            this.listener = pListener;
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);

            Log.i(TAG, "onChange: Music store have changed.");
            loader();
            listener.onChange(tracks);

        }

    }
}
