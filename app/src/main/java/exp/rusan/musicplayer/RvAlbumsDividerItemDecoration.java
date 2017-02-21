package exp.rusan.musicplayer;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Description: To centre item of albums recycler view
 * <!--
 * Author: Rusan
 * Date: 2017/2/21
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class RvAlbumsDividerItemDecoration extends RecyclerView.ItemDecoration {


    public RvAlbumsDividerItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(16, 24, 0, 0);
    }
}
