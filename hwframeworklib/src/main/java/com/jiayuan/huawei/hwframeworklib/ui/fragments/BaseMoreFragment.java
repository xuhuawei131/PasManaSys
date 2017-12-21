package com.jiayuan.huawei.hwframeworklib.ui.fragments;

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
import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.RecyclerViewStateUtils;
import com.jiayuan.huawei.hwframeworklib.utis.ToastUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class BaseMoreFragment<T extends MyBaseRecyclerViewAdapter> extends BaseFragment {
    protected RecyclerView mRecyclerView;
    protected T adapter = null;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;

    protected abstract int getRecyclerViewId();

    protected abstract void findMoreViewByIds();

    protected abstract T getRecyclerViewAdapter();

    protected abstract boolean hasMoreData();

    protected abstract MoreViewResultBean getMoreData();


    @Override
    protected void findViewByIds() {
        mRecyclerView = (RecyclerView) findViewById(getRecyclerViewId());
        adapter = getRecyclerViewAdapter();
        findMoreViewByIds();

        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
        SpacesItemDecoration vDecoration = new SpacesItemDecoration(context, LinearLayoutManager.VERTICAL);
        LinearLayoutManager popupManager = new LinearLayoutManager(context);
        popupManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(popupManager);
        mRecyclerView.addItemDecoration(vDecoration);
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        mRecyclerView.addOnScrollListener(scrollListener);
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
        RecyclerViewStateUtils.setFooterViewStateNoScroll(this.getActivity(), mRecyclerView, 0, LoadingFooter.State.Loading, null);
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
        RecyclerViewStateUtils.setFooterViewStateNoScroll(this.getActivity(), mRecyclerView, 0, LoadingFooter.State.TheEnd, null);
//        RecyclerViewStateUtils.setFooterViewState( mListView, LoadingFooter.State.TheEnd);
    }

    /**
     * 数据异常的时候
     */
    protected void showErrorFootView() {
        RecyclerViewStateUtils.setFooterViewStateNoScroll(this.getActivity(), mRecyclerView, 50, LoadingFooter.State.NetWorkError, null);
    }
}
