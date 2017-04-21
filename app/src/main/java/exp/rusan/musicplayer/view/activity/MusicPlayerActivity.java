package exp.rusan.musicplayer.view.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;

import exp.rusan.musicplayer.R;

public class MusicPlayerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentLayout(R.layout.acti_music_player, new AppBarLayout.ScrollingViewBehavior());

        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, LibraryFragment
                .newInstance()).commit();

    }
}
