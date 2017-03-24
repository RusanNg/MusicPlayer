package exp.rusan.musicplayer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/3/23
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class ArtistDetailRvAdapter extends RecyclerView.Adapter<ArtistDetailRvAdapter.itemViewHolder> {


    public ArtistDetailRvAdapter() {
    }

    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(itemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class itemViewHolder extends RecyclerView.ViewHolder {

        public itemViewHolder(View itemView) {
            super(itemView);
        }
    }

}
