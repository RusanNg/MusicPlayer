package exp.rusan.musicplayer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Map;

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

    private Map<String, Fragment> fragmentMap;

    public LibraryFragmentPagerAdapter(FragmentManager fm, Context pContext) {
        super(fm);
        this.context = pContext;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new Fragment();

        switch (position) {

            case 0:
                fragment = fragmentMap.get("artists");
            break;

            case 1:
                fragment = fragmentMap.get("tracks");
            break;

            case 2:
                fragment = fragmentMap.get("albums");
            break;

            default:
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return VIEW_PAGER_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TabTitles[position];
    }

    public void setFragments(Map pFragmentMap) {
        fragmentMap = pFragmentMap;
    }
}
