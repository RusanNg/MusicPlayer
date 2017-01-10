package exp.rusan.musicplayer;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 监测本地本地音乐库变化
 * <!--
 * Author: Rusan
 * Date: 2017/1/9
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class MusicContentObserver extends ContentObserver{

    private final String TAG = this.getClass().getSimpleName();

    private List<SongBean> songList = new ArrayList<>();

    private Context context;
    private Handler handler;


    /**
     * Creates a content observer.
     *
     * @param pHandler The handler to run {@link #onChange} on, or null if none.
     */
    public MusicContentObserver(Context pContext, Handler pHandler) {
        super(pHandler);
        context = pContext;
        handler = pHandler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);

        Log.i(TAG, "Music store has changed");

        Uri musicContentUri = Media.EXTERNAL_CONTENT_URI;

        songList = MusicLoader.getInstance(context.getContentResolver()).getReloadSongList();

//        Log.i(TAG, "song list num :　" + songList.size());
        handler.obtainMessage(MusicListActivity.whatMusicContentObserver, songList).sendToTarget();

    }
}
