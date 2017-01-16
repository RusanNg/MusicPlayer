package exp.rusan.musicplayer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Description: FragmentPager 适配器
 * <!--
 * Author: Rusan
 * Date: 2017/1/12
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class LibraryFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final int VIEW_PAGER_COUNT = 3;
    private static String[] TabTitles = new String[]{"ARTISTS", "TRACKS", "ALBUMS"};
    private Context context;

    public LibraryFragmentPagerAdapter(FragmentManager fm, Context pContext) {
        super(fm);
        this.context = pContext;
    }

    @Override
    public Fragment getItem(int position) {
        return LibraryPageFragment.newInstance();
    }

    @Override
    public int getCount() {
        return VIEW_PAGER_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TabTitles[position];
    }
}
