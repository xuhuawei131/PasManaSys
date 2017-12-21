package com.jiayuan.huawei.hwframeworklib.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.hwframeworklib.adapter.itemdecorations.SpacesItemDecoration;
import com.jiayuan.huawei.hwframeworklib.ui.customview.moreView.EndlessRecyclerOnScrollListener;
import com.jiayuan.huawei.hwframeworklib.ui.customview.moreView.HeaderAndFooterRecyclerViewAdapter;
import com.jiayuan.huawei.hwframeworklib.ui.customview.moreView.MoreViewResultBean;
import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.JRefreshLayout;
import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.LoadingFooter;
import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.PullToRefreshView;
import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.RecyclerViewStateUtils;
import com.jiayuan.huawei.hwframeworklib.utis.ToastUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * $desc$
 * 刷新 与更多的列表
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class HWRefreshAndMoreViewBaseActivity<T extends MyBaseRecyclerViewAdapter> extends HWBaseWithTitleActivity implements PullToRefreshView.OnRefreshListener {
    protected JRefreshLayout refresh_layout;
    protected RecyclerView mRecyclerView;
    protected T adapter = null;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;

    protected abstract int getRecyclerViewId();
    protected abstract T getRecyclerViewAdapter();
    protected abstract int getJRefreshLayoutId();
    protected abstract void findRefreshAndMoreViewByIds();

    protected abstract boolean hasMoreData();
    protected abstract MoreViewResultBean getMoreData();

    protected abstract void doStartTask();

    protected abstract Object doLongTimeTask();

    protected abstract void doEndingTask(Object o);

    @Override
    protected final void findViewByIds() {
        refresh_layout = (JRefreshLayout)findViewById(getJRefreshLayoutId());
        if (refresh_layout != null) {
            refresh_layout.setOnRefreshListener(this);
        }

        mRecyclerView=(RecyclerView)findViewById(getRecyclerViewId());
        adapter=getRecyclerViewAdapter();

        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
        SpacesItemDecoration vDecoration = new SpacesItemDecoration(this, LinearLayoutManager.VERTICAL);
        LinearLayoutManager popupManager = new LinearLayoutManager(this);
        popupManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(popupManager);
        mRecyclerView.addItemDecoration(vDecoration);
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        mRecyclerView.addOnScrollListener(scrollListener);

        findRefreshAndMoreViewByIds();
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
    private EndlessRecyclerOnScrollListener scrollListener = new EndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);
            if (state == LoadingFooter.State.Loading) {
                return;
            }
            if (hasMoreData()) {
                showLoadingFootView();
                Observable.just("").observeOn(Schedulers.io()).map(new Func1<String, MoreViewResultBean>() {
                    @Override
                    public MoreViewResultBean call(String s) {
                        return getMoreData();
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<MoreViewResultBean>() {
                    @Override
                    public void call(MoreViewResultBean o) {
                        if (o.resultType == MoreViewResultBean.RESULT_TYPE_NONE) {
                            showNormalFootView();
                        } else if (o.resultType == MoreViewResultBean.RESULT_TYPE_NONE) {
                            showEndingFootView();
                        } else {
                            showErrorFootView();
                        }
                    }
                });
            } else {
                showEndingFootView();
                ToastUtil.showActionResult("没有更多数据", false);
            }
        }
    };

    /**
     * 显示正在加载状态
     */
    protected void showLoadingFootView() {
//        RecyclerViewStateUtils.setFooterViewState(mListView,  LoadingFooter.State.Loading);
        RecyclerViewStateUtils.setFooterViewStateNoScroll(this, mRecyclerView, 0, LoadingFooter.State.Loading, null);
    }

    /**
     * 数据加载完毕的时候调用 表示 数据正常列表  没有尾部
     */
    protected void showNormalFootView() {
        RecyclerViewStateUtils.setFooterViewState(this.mRecyclerView, LoadingFooter.State.Normal);
    }
    /**
     * 数据没有的时候 没有更多的时候
     */
    protected void showEndingFootView() {
        RecyclerViewStateUtils.setFooterViewStateNoScroll(this, mRecyclerView, 0, LoadingFooter.State.TheEnd, null);
//        RecyclerViewStateUtils.setFooterViewState( mListView, LoadingFooter.State.TheEnd);
    }

    /**
     * 数据异常的时候
     */
    protected void showErrorFootView() {
        RecyclerViewStateUtils.setFooterViewStateNoScroll(this, mRecyclerView, 50, LoadingFooter.State.NetWorkError, null);
    }

}
