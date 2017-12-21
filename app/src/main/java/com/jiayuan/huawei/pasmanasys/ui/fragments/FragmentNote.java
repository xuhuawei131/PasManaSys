package com.jiayuan.huawei.pasmanasys.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jiayuan.huawei.hwframeworklib.adapter.itemdecorations.GrideSpaceItemDecoration;
import com.jiayuan.huawei.hwframeworklib.adapter.itemdecorations.SpacesItemDecoration;
import com.jiayuan.huawei.hwframeworklib.ui.customview.moreView.HeaderSpanSizeLookup;
import com.jiayuan.huawei.hwframeworklib.ui.customview.moreView.MoreViewResultBean;
import com.jiayuan.huawei.hwframeworklib.ui.fragments.BaseFragment;
import com.jiayuan.huawei.hwframeworklib.ui.fragments.BaseRefreshAndMoreFragment;
import com.jiayuan.huawei.hwframeworklib.utis.DensityUtil;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.NoteAdapter;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao.NoteDao;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.NoteLife;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.OrmPswword;
import com.jiayuan.huawei.pasmanasys.ui.activity.AddNoteActivity;
import com.jiayuan.huawei.pasmanasys.ui.activity.AddPasswordrActivity;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * $desc$
 *记事本
 * @author xuhuawei
 * @time $date$ $time$
 */
public class FragmentNote extends AppBaseFragment<NoteAdapter> implements AppBaseFragment.OnItemDeleteListener,View.OnClickListener {
    private List<NoteLife> arrayList;
    private FloatingActionButton fab;

    @Override
    protected void initData(Bundle bundle) {
        arrayList = new ArrayList<>();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_note;
    }

    @Override
    protected void requestSerivce() {
        onRefresh();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        return manager;
    }
    public RecyclerView.ItemDecoration getSpacesItemDecoration(){
        GrideSpaceItemDecoration spaceItemDecoration=new GrideSpaceItemDecoration(8);
        return spaceItemDecoration;
    }
    @Override
    protected int getRecyclerViewId() {
        return R.id.mRecyclerView;
    }

    @Override
    protected NoteAdapter getRecyclerViewAdapter() {
        return new NoteAdapter(this, arrayList);
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
        int length=arrayList.size();
        if(length>0){
            long time=arrayList.get(length-1).modifyDate;
            List<NoteLife> arrayList=NoteDao.getInstance().getNoteList(time);
            bean.resultType=MoreViewResultBean.RESULT_TYPE_NORMAL;
            bean.result=arrayList;
        }
        return bean;
    }

    @Override
    protected void doStartTask() {

    }

    @Override
    protected Object doLongTimeTask() {
        List<NoteLife> tempList = NoteDao.getInstance().getNoteList(0);
        return tempList;
    }

    @Override
    protected void doEndingTask(Object o) {
        List<NoteLife> tempList = (List<NoteLife>) o;
        if (tempList != null && tempList.size() > 0) {
            arrayList.clear();
            arrayList.addAll(tempList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onMyDestory() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(context, AddNoteActivity.class);
        intent.putExtra("isAdd", true);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onRefresh();
    }

    @Subscriber(tag = "onNoteItemClick")
    public void onNoteItemClick(int position) {
        Intent intent = new Intent();
        intent.setClass(context, AddNoteActivity.class);
        intent.putExtra("param", arrayList.get(position));
        startActivityForResult(intent, 100);
    }
    @Subscriber(tag = "onLongNoteItemClick")
    public void onLongNoteItemClick(int position) {
        deleteItem(position,arrayList.get(position).content,this);
    }
    @Override
    public void onItemDelete(int position) {
        NoteLife bean=arrayList.remove(position);
        adapter.notifyDataSetChanged();
        NoteDao.getInstance().deleteOrmPswword(bean);
    }
}
