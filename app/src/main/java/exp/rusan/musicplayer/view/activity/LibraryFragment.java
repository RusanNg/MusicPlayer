package exp.rusan.musicplayer.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.presenter.AlbumsPresenter;
import exp.rusan.musicplayer.presenter.ArtistsPresenter;
import exp.rusan.musicplayer.presenter.TracksPresenter;
import exp.rusan.musicplayer.view.adapter.LibraryFragmentPagerAdapter;
import exp.rusan.musicplayer.view.fragment.AlbumsFragment;
import exp.rusan.musicplayer.view.fragment.ArtistsFragment;
import exp.rusan.musicplayer.view.fragment.TracksFragment;

public class LibraryFragment extends Fragment {

    private final static String TAG = LibraryFragment.class.getSimpleName();

    private Map<String, Fragment> fragmentMap;

    private TracksFragment tracksFragment;
    private ArtistsFragment artistsFragment;
    private AlbumsFragment albumsFragment;

    private TracksPresenter tracksPresenter;
    private ArtistsPresenter artistsPresenter;
    private AlbumsPresenter albumsPresenter;


    public static LibraryFragment newInstance() {

        LibraryFragment fragment = new LibraryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentMap = new HashMap<>();

        artistsFragment = ArtistsFragment.newInstance();
        tracksFragment = TracksFragment.newInstance();
        albumsFragment = AlbumsFragment.newInstance();

        fragmentMap.put("artists", artistsFragment);
        fragmentMap.put("tracks", tracksFragment);
        fragmentMap.put("albums", albumsFragment);

        setPresenter();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_library, container, false);

        setView(v);

        return v;
    }

    void setView(View v) {


        albumsFragment.setPresenter(albumsPresenter);
        artistsFragment.setPresenter(artistsPresenter);
        tracksFragment.setPresenter(tracksPresenter);

        // TabLayout and ViewPager
        ViewPager vpLibrary = (ViewPager) v.findViewById(R.id.vp_library);

        LibraryFragmentPagerAdapter vpLibraryAdapter = new LibraryFragmentPagerAdapter
                (getChildFragmentManager(), getContext());
        vpLibraryAdapter.setFragments(fragmentMap);
        vpLibrary.setAdapter(vpLibraryAdapter);

        TabLayout tlLibrary = (TabLayout) v.findViewById(R.id.tl_library);
        tlLibrary.setupWithViewPager(vpLibrary);
    }

    void setPresenter() {
        albumsPresenter = new AlbumsPresenter(getContext(),
                albumsFragment);
        tracksPresenter = new TracksPresenter(getContext(),
                tracksFragment);
        artistsPresenter = new ArtistsPresenter(getContext(),
                artistsFragment);
    }



}
