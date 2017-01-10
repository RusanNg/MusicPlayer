package exp.rusan.musicplayer;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 使用CotentResolver(Content Provider)获取本地音乐库信息
 * <!--
 * Author: Rusan
 * Date: 2017/1/6
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class MusicLoader {

    public final String TAG = this.getClass().getSimpleName();

    private List<SongBean> songList;

    private static MusicLoader musicLoader;

    private static ContentResolver contentResolver;

    private Uri contentUri = Media.EXTERNAL_CONTENT_URI;

    private String[] projection = {
            Media.TITLE,
            Media.ARTIST,
            Media.ALBUM,
            Media.DURATION
    };

    private String where = "mime_type in ('audio/mpeg', 'audio/x-ms-wma') and TITLE" +
            " <> 'audio' and is_music > 0";

    private String sortOrder = Media.DATA;

    public static MusicLoader getInstance(ContentResolver pContentResolver) {
        if (musicLoader == null) {
            contentResolver = pContentResolver;
            musicLoader = new MusicLoader();
        }
        return musicLoader;
    }

    private MusicLoader() {
        loader();
    }

    private void loader() {
        songList = new ArrayList<>();

        Cursor cursor = contentResolver.query(contentUri, projection, where, null, sortOrder);
        if (cursor == null) {
            Log.v(TAG, "Music Loader cursor == null.");
        } else if (!cursor.moveToFirst()) {
            Log.v(TAG, "Music Loader cursor.moveToFirst return false.");
        } else {
            int titleCol = cursor.getColumnIndex(Media.TITLE);
            int artistCol = cursor.getColumnIndex(Media.ARTIST);
            int albumCol = cursor.getColumnIndex(Media.ALBUM);
            int durationCol = cursor.getColumnIndex(Media.DURATION);

            do {
                String title = cursor.getString(titleCol);
                String artist = cursor.getString(artistCol);
                String album = cursor.getString(albumCol);
                int duration = cursor.getInt(durationCol);

                songList.add(new SongBean(title, artist, album, duration));

            } while (cursor.moveToNext());
        }
    }

    public List<SongBean> getReloadSongList() {
        loader();
        return songList;
    }

    public List<SongBean> getSongList() {
        return songList;
    }

}
