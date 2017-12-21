package com.jiayuan.huawei.pasmanasys.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.jiayuan.huawei.hwframeworklib.ui.customview.moreView.MoreViewResultBean;
import com.jiayuan.huawei.hwframeworklib.ui.fragments.BaseRefreshAndMoreFragment;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.NoticeAdapter;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao.NoticeDao;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.NoticeBean;
import com.jiayuan.huawei.pasmanasys.ui.activity.AddHomeActivity;
import com.jiayuan.huawei.pasmanasys.ui.activity.AddNoticeActivity;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * $desc$
 * 每日谨记
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class FragmentNotice extends AppBaseFragment<NoticeAdapter> implements AppBaseFragment.OnItemDeleteListener,View.OnClickListener {
    private List<NoticeBean> arrayList;
    private FloatingActionButton fab;

    @Override
    protected void initData(Bundle bundle) {
        arrayList = new ArrayList<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_notice;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.mRecyclerView;
    }

    @Override
    protected NoticeAdapter getRecyclerViewAdapter() {
        return new NoticeAdapter(this, arrayList);
    }

    @Override
    protected int getJRefreshLayoutId() {
        return R.id.mJRefreshLayout;
    }

    @Override
    protected void findRefreshAndMoreViewByIds() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        emptyView=findViewById(R.id.emptyView);
        mRecyclerView.setEmptyView(emptyView);
    }

    @Override
    protected MoreViewResultBean getMoreData(MoreViewResultBean bean) {
        int length = arrayList.size();
        if (length > 0) {
            long time = arrayList.get(length - 1).modifyDate;
            List<NoticeBean> tempList = NoticeDao.getInstance().getNoticeList(time);
            if (tempList != null && tempList.size() > 0) {
                bean.resultType = MoreViewResultBean.RESULT_TYPE_NORMAL;
                bean.result = arrayList;
            }
        }
        return bean;
    }

    @Override
    protected void doStartTask() {

    }

    @Override
    protected Object doLongTimeTask() {
        List<NoticeBean> arrayList = NoticeDao.getInstance().getNoticeList(0);
        return arrayList;
    }

    @Override
    protected void doEndingTask(Object o) {
        List<NoticeBean> tempList = (List<NoticeBean>) o;
        if (tempList != null && tempList.size() > 0) {
            arrayList.clear();
            arrayList.addAll(tempList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void requestSerivce() {
        onRefresh();
    }

    @Override
    protected void onMyDestory() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(context, AddNoticeActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onRefresh();
    }

    @Subscriber(tag = "onNoticeItemClick")
    public void onNoticeItemClick(int position) {
        Intent intent = new Intent();
        intent.setClass(context, AddNoticeActivity.class);
        intent.putExtra("param", arrayList.get(position));
        startActivityForResult(intent, 100);
    }
    @Subscriber(tag = "onLongNoticeItemClick")
    public void onLongNoticeItemClick(int position) {
        deleteItem(position,arrayList.get(position).content,this);
    }
    @Override
    public void onItemDelete(int position) {
        NoticeBean bean=arrayList.remove(position);
        adapter.notifyDataSetChanged();
        NoticeDao.getInstance().deleteNotice(bean);
    }
}
