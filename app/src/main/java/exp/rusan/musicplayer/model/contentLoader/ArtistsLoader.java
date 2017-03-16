package exp.rusan.musicplayer.model.contentLoader;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.bean.Artist;
import exp.rusan.musicplayer.model.ITrackStoreModel;
import exp.rusan.musicplayer.model.TrackStore;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/3/1
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class ArtistsLoader {

    private static final String TAG = "ArtistsLoader";

    private List<Artist> artists;

    private Context context;

    private ArtistsContentObserver contentObserver;

    private Uri artistsUri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;

    private String[] projection = {
            MediaStore.Audio.Artists._ID,
            MediaStore.Audio.Artists.ARTIST,
            MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,
            MediaStore.Audio.Artists.NUMBER_OF_TRACKS
    };

    private String selection = "number_of_tracks >= 1";

    private String sortOrder = MediaStore.Audio.Artists.ARTIST;

    public ArtistsLoader(Context pContext, ITrackStoreModel.OnDataChangeListener pListener) {

        this.context = pContext.getApplicationContext();

        contentObserver = new ArtistsContentObserver(pListener);

        regigisterContentObserver();

        Log.i(TAG, "ArtistsLoader: here!!!");

        loader();

    }

    void regigisterContentObserver() {
        context.getContentResolver().registerContentObserver(artistsUri, false, contentObserver);
    }

    private void loader() {

        artists = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(artistsUri, projection, selection, null, sortOrder);

        if (cursor == null) {
            Log.i(TAG, "loader: cursor is null");
        } else if (!cursor.moveToFirst()) {
            Log.i(TAG, "loader: cursor is null at first.");
        } else {

            int idCol = cursor.getColumnIndex(MediaStore.Audio.Artists._ID);
            int titleCol = cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST);
            int numAlbumsCol = cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS);
            int numTracksCol = cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS);

            do {

                int id = cursor.getInt(idCol);
                String title = cursor.getString(titleCol);
                int numAlbums = cursor.getInt(numAlbumsCol);
                int numTracks = cursor.getInt(numTracksCol);

                Album a = TrackStore.getInstance().getAlbumByArtistId(id);

                Logger.i(String.valueOf(id));

                TrackStore.getInstance().addArtist(new Artist.Builder(id, title).numTracks
                        (numTracks).numAlbums(numAlbums).artUri(a.getArtUri()).build());

            } while (cursor.moveToNext());
        }

        cursor.close();

        Log.i(TAG, "loader: artists size : " + artists.size());
    }



    /**
     * ArtistsContentObserver is to listen to artists data, when artists data changes, the method
     * onChanges will be invoked.
     */
    private class ArtistsContentObserver extends ContentObserver {

        private ITrackStoreModel.OnDataChangeListener listener;

        /**
         * Creates a content observer.
         */
        public ArtistsContentObserver(ITrackStoreModel.OnDataChangeListener pListener) {
            super(new Handler());
            this.listener = pListener;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            loader();
            listener.onChange(artists);

        }
    }



}
