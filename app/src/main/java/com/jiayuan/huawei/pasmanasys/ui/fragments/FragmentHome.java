package com.jiayuan.huawei.pasmanasys.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.hwframeworklib.ui.customview.moreView.MoreViewResultBean;
import com.jiayuan.huawei.hwframeworklib.ui.fragments.BaseFragment;
import com.jiayuan.huawei.hwframeworklib.ui.fragments.BaseRefreshAndMoreFragment;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.HomeAdapter;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao.HomeDao;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.HomeBean;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.NoteLife;
import com.jiayuan.huawei.pasmanasys.ui.activity.AddHomeActivity;

import org.simple.eventbus.Subscriber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * $desc$
 * 每日谨记
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class FragmentHome extends AppBaseFragment<HomeAdapter> implements AppBaseFragment.OnItemDeleteListener,View.OnClickListener {
    private List<HomeBean> arrayList;
    private FloatingActionButton fab;

    @Override
    protected void initData(Bundle bundle) {
        arrayList = new ArrayList<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.mRecyclerView;
    }

    @Override
    protected HomeAdapter getRecyclerViewAdapter() {
        return new HomeAdapter(this, arrayList);
    }

    @Override
    protected int getJRefreshLayoutId() {
        return R.id.mJRefreshLayout;
    }

    @Override
    protected void findRefreshAndMoreViewByIds() {
        emptyView=findViewById(R.id.emptyView);
        mRecyclerView.setEmptyView(emptyView);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    protected MoreViewResultBean getMoreData(MoreViewResultBean bean) {
        int length = arrayList.size();
        if (length > 0) {
            long time = arrayList.get(length - 1).modifyDate;
            List<HomeBean> tempList = HomeDao.getInstance().getHomeList(time);
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
        List<HomeBean> arrayList = HomeDao.getInstance().getHomeList(0);
        return arrayList;
    }

    @Override
    protected void doEndingTask(Object o) {
        List<HomeBean> tempList = (List<HomeBean>) o;
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
        intent.setClass(context, AddHomeActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onRefresh();
    }

    @Subscriber(tag = "onHomeItemClick")
    public void onHomeItemClick(int position) {
        Intent intent = new Intent();
        intent.setClass(context, AddHomeActivity.class);
        intent.putExtra("param", arrayList.get(position));
        startActivityForResult(intent, 100);
    }

    @Subscriber(tag = "onLongHomeItemClick")
    public void onLongHomeItemClick(int position) {
        deleteItem(position,arrayList.get(position).content,this);
    }

    @Override
    public void onItemDelete(int position) {
        HomeBean bean=arrayList.remove(position);
        adapter.notifyDataSetChanged();
        HomeDao.getInstance().deleteHome(bean);
    }
}
