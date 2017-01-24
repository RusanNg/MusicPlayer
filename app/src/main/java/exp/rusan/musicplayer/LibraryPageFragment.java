package exp.rusan.musicplayer;

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

public class LibraryPageFragment extends Fragment implements ITracksContract.IView {


    private final String TAG = this.getClass().getSimpleName();

    private ITracksContract.IPresenter presenter;

    RecyclerView rvTracks;

    private TracksAdapter tracksAdapter;

    public static LibraryPageFragment newInstance() {
        return new LibraryPageFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new TracksPresenter(getContext(), this);
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

        View v = inflater.inflate(R.layout.fragment_library_page, container, false);

        // Track List
        rvTracks = (RecyclerView) v.findViewById(R.id.rv_tracks);
        rvTracks.setHasFixedSize(true);
        rvTracks.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTracks.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration
                .VERTICAL_LIST));

        tracksAdapter = new TracksAdapter(onItemClickListener);

        rvTracks.setAdapter(tracksAdapter);


        return v;

    }


    TracksAdapter.OnItemClickListener onItemClickListener = new TracksAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Track track = tracksAdapter.getTrack(position);
            Log.d(TAG, track.getTitle());

        }
    };

    @Override
    public void setPlayingIndicator(boolean isPlaying) {

    }

    @Override
    public void showTracks(List<Track> pTracks) {
        Log.i(TAG, "Tracks num : " + pTracks.size());
        tracksAdapter.setTracks(pTracks);
    }

    @Override
    public void showTrackOptionsUi(Integer id) {

    }

    @Override
    public void showReloadTracks(List<Track> pTracks) {
        tracksAdapter.setTracks(pTracks);
    }


    @Override
    public void setPresenter(ITracksContract.IPresenter pIPresenter) {
        this.presenter = pIPresenter;
    }
}
