package exp.rusan.musicplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * <!--
 * Author: Rusan
 * Date: 2017/3/21
 * Version: 0.0.1
 * ---------------------------------------------
 * History:
 * <p>
 * -->
 */

public class HeaderView extends LinearLayout {

    @BindView(R.id.tv_header_view_title)
    TextView title;

    @BindView(R.id.tv_header_view_subtitle)
    TextView subTitle;

    public HeaderView(Context context) {
        super(context);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }


    public void setTitle(String title) {
        if (title == null) {
            hideOrSetText(this.title, "<Unknown>");
        } else {
            hideOrSetText(this.title, title);
        }
    }

    public void setSubtitle(String subtitle) {
        hideOrSetText(this.subTitle, subtitle);
    }

    public void setTitle(String title, String subtitle) {
        setTitle(title);
        setSubtitle(subtitle);
    }

    private void hideOrSetText(TextView tv, String text) {
        if (text == null || text.equals("")) {
            tv.setVisibility(GONE);
        } else {
            tv.setText(text);
        }

    }














}
