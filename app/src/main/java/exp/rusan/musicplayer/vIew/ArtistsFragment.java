package exp.rusan.musicplayer.vIew;


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

import exp.rusan.musicplayer.OnItemClickListener;
import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.RvTracksDividerItemDecoration;
import exp.rusan.musicplayer.bean.Artist;
import exp.rusan.musicplayer.constract.IArtistsPageContract;
import exp.rusan.musicplayer.vIew.adapter.ArtistsRecyclerViewAdapter;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/2/28
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class ArtistsFragment extends Fragment implements IArtistsPageContract.IArtistsView{

    private final static String TAG = "ArtistsPage";

    private IArtistsPageContract.IArtistsPresenter presenter;

    private RecyclerView rvArtists;

    private ArtistsRecyclerViewAdapter adapter;

    public static ArtistsFragment newInstance() {

        ArtistsFragment fragment = new ArtistsFragment();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        adapter = new ArtistsRecyclerViewAdapter(getContext(), onItemClickListener);

        View v = inflater.inflate(R.layout.fragment_library_artists_page, container, false);

        rvArtists = (RecyclerView) v.findViewById(R.id.rv_artists);

        rvArtists.setLayoutManager(new LinearLayoutManager(getContext()));
        rvArtists.addItemDecoration(new RvTracksDividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL));
        rvArtists.setHasFixedSize(true);
        rvArtists.setAdapter(adapter);



        return v;
    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Log.i(TAG, "onItemClick: item clicked");
        }
    };



    @Override
    public void showArtists(List<Artist> pArtists) {
        adapter.setArtists(pArtists);
    }

    @Override
    public void showReloadArtists(List<Artist> pArtists) {
        adapter.setArtists(pArtists);
    }

    @Override
    public void setPresenter(IArtistsPageContract.IArtistsPresenter pPresenter) {
        this.presenter = pPresenter;
    }
}
