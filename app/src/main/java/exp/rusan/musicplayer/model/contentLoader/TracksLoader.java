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

import exp.rusan.musicplayer.bean.Track;
import exp.rusan.musicplayer.model.ITrackStoreModel;
import exp.rusan.musicplayer.model.TrackStore;

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

public class TracksLoader {

    private static final String TAG = "TracksLoader";

    private List<Track> tracks;

    private Context context;

    private TracksContentObserver tracksContentObserver;

    private Uri tracksUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    private String[] projection = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,

            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM

    };

    private String where = "is_music > 0";


    private String sortOrder = MediaStore.Audio.Media.TITLE;


    public TracksLoader(Context pContext, ITrackStoreModel.OnDataChangeListener pListener) {
        this.context = pContext.getApplicationContext();

        tracksContentObserver = new TracksContentObserver(pListener);
        registerTracksContentObserver();

        loader();

//        Logger.i(TAG, "TracksLoader: : " + tracksUri);

    }

    /**
     * Registe observer for tracks content resolver.
     */
    private void registerTracksContentObserver() {
        context.getContentResolver().registerContentObserver(tracksUri, false, tracksContentObserver);
    }


    private void loader() {
        tracks = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(tracksUri, projection, where, null, sortOrder);
        if (cursor == null) {
            Log.v(TAG, "Music Loader cursor == null.");
        } else if (!cursor.moveToFirst()) {
            Log.v(TAG, "Music Loader cursor.moveToFirst return false.");
        } else {
            int idCol = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int titleCol = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int durationCol = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int dataUriCol = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int artistIdCol = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID);
            int albumIdCol = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            int artistCol = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumCol = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);

            do {
                int id = cursor.getInt(idCol);
                String title = cursor.getString(titleCol);
                String dataUri = cursor.getString(dataUriCol);
                int duration = cursor.getInt(durationCol);
                int artistId = cursor.getInt(artistIdCol);
                int albumId = cursor.getInt(albumIdCol);
                String artist = cursor.getString(artistCol);
                String album = cursor.getString(albumCol);

                tracks.add(new Track.Builder(id, title).duration(duration)
                        .dataUri(dataUri).artistTitle(artist).albumTitle(album).artistId(artistId)
                        .albumId(albumId).build());

            } while (cursor.moveToNext());

            TrackStore.getInstance().setTracks(tracks);

        }

        cursor.close();
    }

    /**
     * TrackContentObserver to listen tracks data changes. The method onChange will be invoked
     * when data change.
     */
    private class TracksContentObserver extends ContentObserver{

        private ITrackStoreModel.OnDataChangeListener listener;

        public TracksContentObserver(ITrackStoreModel.OnDataChangeListener pListener) {
            super(new Handler());
            this.listener = pListener;
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);

//            Log.i(TAG, "onChange: Music store have changed.");

//            TrackStore.getInstance().emptyTracks();
            loader();
            listener.onChange();

        }

    }
}
