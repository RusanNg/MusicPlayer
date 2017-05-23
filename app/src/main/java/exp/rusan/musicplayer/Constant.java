package exp.rusan.musicplayer;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Window;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rusan on 2017/3/27.
 */

public class Constant {

    public static final String ARTIST_ID = "artist_id";
    public static final String ALBUM_ID = "album_id";

    public static String numAlbumsToString(Application app, int num){

        String str;

        if (isSingular(num)) {
            str = num + app.getString(R.string.album);
        } else {
            str = num + app.getString(R.string.albums);
        }

        return str;
    }

    public static String numTracksToString(Application app, int num){

        String str;

        if (isSingular(num)) {
            str = num + app.getString(R.string.track);
        } else {
            str = num + app.getString(R.string.tracks);
        }

        return str;
    }

    public static String durationToString(int duration) {

        if (duration >= 72000000) {

            return new SimpleDateFormat("hh:mm:ss").format(new Date(duration));

        } else {

           return new SimpleDateFormat("mm:ss").format(new Date(duration));

        }

    }

    private static boolean isSingular(int num) {
        if (num > 1) {
            return false;
        } else {
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setTranslucentStatus(Activity activity) {
        Window window = activity.getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(activity.getResources().getColor(R.color.colorTransparent));
    }

}
