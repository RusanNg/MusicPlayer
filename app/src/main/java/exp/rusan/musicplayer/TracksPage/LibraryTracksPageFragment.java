package exp.rusan.musicplayer.TracksPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import exp.rusan.musicplayer.DividerItemDecoration;
import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.TrackStore.Track;

/**
 * Description: Library fragment 配合 TabLayout 和 ViewPager 使用
 * <!--
 * Author: Rusan
 * Date: 2017/1/12
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class LibraryTracksPageFragment extends Fragment implements ITracksPageContract.ITracksPageView {


    private final String TAG = this.getClass().getSimpleName();

    private ITracksPageContract.ITracksPagePresenter presenter;

    RecyclerView rvTracks;

    private static LibraryTracksPageFragment fragment;

    private TracksRecyclerViewAdapter tracksRecyclerViewAdapter;

    public static LibraryTracksPageFragment newInstance() {
        return new LibraryTracksPageFragment();
    }

    public static LibraryTracksPageFragment getInstance() {
        if (fragment == null) {
            fragment = new LibraryTracksPageFragment();
        }
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new TracksTracksPagePresenter(getContext(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_library_tracks_page, container, false);

        // Track List
        rvTracks = (RecyclerView) v.findViewById(R.id.rv_tracks);
        rvTracks.setHasFixedSize(true);
        rvTracks.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTracks.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration
                .VERTICAL_LIST));

        tracksRecyclerViewAdapter = new TracksRecyclerViewAdapter(onItemClickListener);

        rvTracks.setAdapter(tracksRecyclerViewAdapter);


        return v;

    }


    TracksRecyclerViewAdapter.OnItemClickListener onItemClickListener = new TracksRecyclerViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            //// TODO: 2017/2/14 Track item click
            Track track = tracksRecyclerViewAdapter.getTrack(position);
            Log.d(TAG, track.getTitle());

        }
    };

    @Override
    public void setPlayingIndicator(boolean isPlaying) {

    }

    @Override
    public void showTracks(List<Track> pTracks) {
        Log.i(TAG, "Tracks num : " + pTracks.size());
        tracksRecyclerViewAdapter.setTracks(pTracks);
    }

    @Override
    public void showTrackOptionsUi(Integer id) {

    }

    @Override
    public void showReloadTracks(List<Track> pTracks) {
        tracksRecyclerViewAdapter.setTracks(pTracks);
    }


    @Override
    public void setPresenter(ITracksPageContract.ITracksPagePresenter pITracksPagePresenter) {
        this.presenter = pITracksPagePresenter;
    }
}
