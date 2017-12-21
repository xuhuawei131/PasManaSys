package com.jiayuan.huawei.pasmanasys.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.jiayuan.huawei.hwframeworklib.ui.customview.moreView.MoreViewResultBean;
import com.jiayuan.huawei.hwframeworklib.ui.fragments.BaseRefreshAndMoreFragment;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.PasswordAdapter;
import com.jiayuan.huawei.pasmanasys.persistData.comDb.model.PasswordBean;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao.NoteDao;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao.PasswordDao;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.NoteLife;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.OrmPswword;
import com.jiayuan.huawei.pasmanasys.ui.activity.AddPasswordrActivity;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class FragmentPassword extends AppBaseFragment<PasswordAdapter> implements AppBaseFragment.OnItemDeleteListener,View.OnClickListener {
    private List<OrmPswword> arrayList;
    private FloatingActionButton fab;

    @Override
    protected void initData(Bundle bundle) {
        arrayList = new ArrayList<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_password;
    }


    @Override
    protected void onMyDestory() {

    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.mRecyclerView;
    }

    @Override
    protected PasswordAdapter getRecyclerViewAdapter() {
        return new PasswordAdapter(this, arrayList);
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
            List<OrmPswword> arrayList = PasswordDao.getInstance().getOrmPswwordList(time);
            bean.resultType = MoreViewResultBean.RESULT_TYPE_NORMAL;
            bean.result = arrayList;
        }
        return bean;
    }

    @Override
    protected void doStartTask() {

    }

    @Override
    protected Object doLongTimeTask() {
        List<OrmPswword> tempList = PasswordDao.getInstance().getOrmPswwordList(0);
        return tempList;
    }

    @Override
    protected void doEndingTask(Object o) {
        List<OrmPswword> tempList = (List<OrmPswword>) o;
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
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(context, AddPasswordrActivity.class);
        intent.putExtra("isAdd", true);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onRefresh();
    }

    @Subscriber(tag = "onPasswordItemClick")
    public void onPasswordItemClick(int position) {
        Intent intent = new Intent();
        intent.setClass(context, AddPasswordrActivity.class);
        intent.putExtra("param", arrayList.get(position));
        startActivityForResult(intent, 100);
    }
    @Subscriber(tag = "onLongPasswordItemClick")
    public void onLongPasswordItemClick(int position) {
        deleteItem(position,arrayList.get(position).content,this);
    }
    @Override
    public void onItemDelete(int position) {
        OrmPswword bean=arrayList.remove(position);
        adapter.notifyDataSetChanged();
        PasswordDao.getInstance().deleteOrmPswword(bean);
    }
}
