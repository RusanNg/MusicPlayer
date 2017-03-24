package exp.rusan.musicplayer;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

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

public class HeaderViewBehavior extends CoordinatorLayout.Behavior<HeaderView> {


    private Context context;

    private int startMarginLeft;
    private int endMarginLeft;
    private int marginRight;
    private int startMarginBottom;
    private boolean isHide;

    public HeaderViewBehavior(Context pContext) {
        context = pContext;
    }

    public HeaderViewBehavior(Context pContext, AttributeSet pAttrs) {
        this.context = pContext;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, HeaderView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, HeaderView child, View dependency) {

        shouldInitProperties(child, dependency);

        int maxScroll = ((AppBarLayout) dependency).getTotalScrollRange();
        float percentage = Math.abs(dependency.getY()) / (float)maxScroll;

        float childPosition = dependency.getHeight()
                + dependency.getY()
                - child.getHeight()
                - (getToolbarHeight() - child.getHeight()) * percentage / 2;

        childPosition = childPosition - startMarginBottom * (1f - percentage);

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        lp.leftMargin = (int) ((percentage * endMarginLeft) + startMarginLeft);
        lp.rightMargin = marginRight;
        child.setLayoutParams(lp);

        child.setY(childPosition);


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (isHide && percentage < 1) {
                child.setVisibility(View.VISIBLE);
                isHide = false;
            } else if (!isHide && percentage == 1) {
                child.setVisibility(View.GONE);
                isHide = true;
            }
        }

        return true;

    }

    private void shouldInitProperties(HeaderView pChild, View pDependency) {

        if (startMarginLeft == 0) {
            startMarginLeft = context.getResources().getDimensionPixelOffset(R.dimen
                    .header_view_start_margin_left);
        }

        if (endMarginLeft == 0) {
            endMarginLeft = context.getResources().getDimensionPixelOffset(R.dimen.header_view_end_margin_left);
        }

        if (startMarginBottom == 0) {
            startMarginBottom = context.getResources().getDimensionPixelOffset(R.dimen.header_view_start_margin_bottom);
        }

        if (marginRight == 0) {
            marginRight = context.getResources().getDimensionPixelOffset(R.dimen
                    .header_view_end_margin_right);
        }

    }

    public int getToolbarHeight() {
        int result = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            result = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return result;
    }
}
