package com.jiayuan.huawei.hwframeworklib.ui.customview.appBarLayouExpland;


import android.support.design.widget.AppBarLayout;

/**
 * $desc$
 * CollapsingToolbarLayout的展开与折叠监听器
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0619/4362.html
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }
    private State mCurrentState = State.IDLE;
    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }
}
