package com.jiayuan.huawei.hwframeworklib.ui.activity;

import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.JRefreshLayout;
import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.PullToRefreshView;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * $desc$
 * 刷新
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class HWRefreshBaseActivity extends HWBaseWithTitleActivity implements PullToRefreshView.OnRefreshListener {
    protected JRefreshLayout refresh_layout;

    protected abstract int getJRefreshLayoutId();

    protected abstract void findRefreshViewByIds();

    protected abstract void doStartTask();

    protected abstract Object doLongTimeTask();

    protected abstract void doEndingTask(Object o);

    @Override
    protected final void findViewByIds() {
        refresh_layout = (JRefreshLayout)findViewById(getJRefreshLayoutId());
        if (refresh_layout != null) {
            refresh_layout.setOnRefreshListener(this);
        }
        findRefreshViewByIds();

    }

    @Override
    public final void onRefresh() {
        if (refresh_layout != null && !refresh_layout.isRefreshing()) {
            refresh_layout.setRefreshing(true);
        }
        doStartTask();
        Observable.just("").subscribeOn(Schedulers.io())
                .map(new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        return doLongTimeTask();
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (refresh_layout != null && refresh_layout.isRefreshing()) {
                    refresh_layout.setRefreshing(false);
                }
                doEndingTask(o);
            }
        });
    }
}
