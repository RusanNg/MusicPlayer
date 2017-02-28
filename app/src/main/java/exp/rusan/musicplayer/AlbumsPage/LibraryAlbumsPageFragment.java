package exp.rusan.musicplayer.AlbumsPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import exp.rusan.musicplayer.OnItemClickListener;
import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.RvAlbumsDividerItemDecoration;
import exp.rusan.musicplayer.TrackStore.Album;

/**
 * Description:Albums page fragment in Library
 * <!--
 * Author: Rusan
 * Date: 2017/2/14
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class LibraryAlbumsPageFragment extends Fragment implements IAlbumsContract.IAblumsPageView {

    private final String TAG = this.getClass().getSimpleName();

    private IAlbumsContract.IAblumsPagePresenter presenter;

    private AlbumsRecyclerViewAdapter adapter;

    private static LibraryAlbumsPageFragment fragment;


    public static LibraryAlbumsPageFragment getInstance() {
        if (fragment == null) {
            fragment = new LibraryAlbumsPageFragment();
        }
        return fragment;
    }


    private LibraryAlbumsPageFragment() {
//        presenter = new AlbumsPagePresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new AlbumsPagePresenter(getContext(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        adapter = new AlbumsRecyclerViewAdapter(getContext(), onItemClickListener);

        View v = inflater.inflate(R.layout.fragment_library_albums_page, container, false);

        RecyclerView rvAlbums = (RecyclerView) v.findViewById(R.id.rv_albums);

        GridLayoutManager gm = new GridLayoutManager(getContext(), 2);


        rvAlbums.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvAlbums.setAdapter(adapter);
        rvAlbums.setHasFixedSize(true);
        rvAlbums.addItemDecoration(new RvAlbumsDividerItemDecoration());



        return v;
    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            // TODO: 2017/2/14 album item click
            Log.i(TAG, "onItemClick: item clicked!!!");
        }
    };

    @Override
    public void showAlbums(List<Album> pAlbums) {
        adapter.setAlbums(pAlbums);
    }

    @Override
    public void showReloadAlbums(List<Album> pAlbums) {
        adapter.setAlbums(pAlbums);
    }

    @Override
    public void setPresenter(IAlbumsContract.IAblumsPagePresenter pPresenter) {
        this.presenter = pPresenter;
    }
}
