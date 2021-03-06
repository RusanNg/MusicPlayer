package exp.rusan.musicplayer.view.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import exp.rusan.musicplayer.Constant;
import exp.rusan.musicplayer.OnItemClickListener;
import exp.rusan.musicplayer.R;
import exp.rusan.musicplayer.RvAlbumsDividerItemDecoration;
import exp.rusan.musicplayer.bean.Album;
import exp.rusan.musicplayer.constract.IAlbumsPageContract;
import exp.rusan.musicplayer.view.activity.AlbumDetailActivity;
import exp.rusan.musicplayer.view.adapter.AlbumsRecyclerViewAdapter;

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

public class AlbumsFragment extends Fragment implements IAlbumsPageContract.IAblumsPageView {

    private final String TAG = this.getClass().getSimpleName();

    public final static String TRANSITION_NAME = "art_albums_to_detail";

    private IAlbumsPageContract.IAlbumsPagePresenter presenter;

    private AlbumsRecyclerViewAdapter adapter;

    public static AlbumsFragment newInstance() {

        AlbumsFragment fragment = new AlbumsFragment();

        return fragment;
    }


    public AlbumsFragment() {
//        presenter = new AlbumsPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        this.presenter = new AlbumsPresenter(getContext(), this);
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

        View v = inflater.inflate(R.layout.frag_libr_albums_page, container, false);

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
        public void onItemClick(int position, View v) {
            // TODO: 2017/2/14 album item click
            Log.i(TAG, "onItemClick: item clicked!!!");

            ImageView iv = (ImageView) v.findViewById(R.id.iv_item_albums_art);

            Intent i = new Intent(getContext(), AlbumDetailActivity.class);

            i.putExtra(Constant.ALBUM_ID, adapter.getAlbumByPosition(position).getId());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(getActivity(), iv,
                        TRANSITION_NAME).toBundle());
            } else {
                startActivity(i);
            }

//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
//                    AlbumDetailActivity.newInstance()).addToBackStack(null).commit();

//            getFragmentManager().beginTransaction()

        }
    };

    @Override
    public void showAlbums(List<Album> pAlbums) {
        adapter.setAlbums(pAlbums);
    }

    @Override
    public void showReloadAlbums(List<Album> pAlbums) {

        if (adapter != null) {
            adapter.setAlbums(pAlbums);
        }
    }

    @Override
    public void setPresenter(IAlbumsPageContract.IAlbumsPagePresenter pPresenter) {
        this.presenter = pPresenter;
    }
}
