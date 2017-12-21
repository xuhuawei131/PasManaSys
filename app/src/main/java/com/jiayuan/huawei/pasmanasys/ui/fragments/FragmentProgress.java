package com.jiayuan.huawei.pasmanasys.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.jiayuan.huawei.hwframeworklib.ui.customview.moreView.MoreViewResultBean;
import com.jiayuan.huawei.hwframeworklib.ui.fragments.BaseRefreshAndMoreFragment;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.ProgressAdapter;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao.HomeDao;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao.ProgressDao;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.HomeBean;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.ProgressBean;
import com.jiayuan.huawei.pasmanasys.ui.activity.AddPasswordrActivity;
import com.jiayuan.huawei.pasmanasys.ui.activity.AddProgressActivity;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class FragmentProgress extends AppBaseFragment<ProgressAdapter> implements AppBaseFragment.OnItemDeleteListener,View.OnClickListener {
    private List<ProgressBean> arrayList;
    private FloatingActionButton fab;

    @Override
    protected void initData(Bundle bundle) {
        arrayList = new ArrayList<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_progress;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.mRecyclerView;
    }

    @Override
    protected ProgressAdapter getRecyclerViewAdapter() {
        return new ProgressAdapter(this, arrayList);
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
            List<ProgressBean> tempList = ProgressDao.getInstance().getProgressList(time);
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
        List<ProgressBean> tempList = ProgressDao.getInstance().getProgressList(0);
        return tempList;
    }

    @Override
    protected void doEndingTask(Object o) {
        List<ProgressBean> tempList=(List<ProgressBean>)o;
        if(tempList!=null&&tempList.size()>0){
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
        intent.setClass(context, AddProgressActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onRefresh();
    }

    @Subscriber(tag = "onProgressItemClick")
    public void onProgressItemClick(int position) {
        Intent intent=new Intent();
        intent.setClass(context,AddProgressActivity.class);
        intent.putExtra("param",arrayList.get(position));
        startActivityForResult(intent,100);
    }
    @Subscriber(tag = "onLongProgressItemClick")
    public void onLongProgressItemClick(int position) {
        deleteItem(position,arrayList.get(position).content,this);
    }
    @Override
    public void onItemDelete(int position) {
        ProgressBean bean=arrayList.remove(position);
        adapter.notifyDataSetChanged();
        ProgressDao.getInstance().deleteProgress(bean);
    }
}
