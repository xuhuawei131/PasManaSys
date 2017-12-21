package com.jiayuan.huawei.pasmanasys.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.jiayuan.huawei.hwframeworklib.adapter.itemdecorations.SpacesItemDecoration;
import com.jiayuan.huawei.hwframeworklib.persistData.commdb.database.MySQLiteDatabase;
import com.jiayuan.huawei.hwframeworklib.ui.activity.HWBaseWithTitleActivity;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.NavigationAdapter;
import com.jiayuan.huawei.pasmanasys.bean.NavigationBean;
import com.jiayuan.huawei.pasmanasys.ui.fragments.FragmentHome;
import com.jiayuan.huawei.pasmanasys.ui.fragments.FragmentNote;
import com.jiayuan.huawei.pasmanasys.ui.fragments.FragmentNotice;
import com.jiayuan.huawei.pasmanasys.ui.fragments.FragmentPassword;
import com.jiayuan.huawei.pasmanasys.ui.fragments.FragmentProgress;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends HWBaseWithTitleActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private NavigationAdapter naviAdapter;
    private List<NavigationBean> naviList;
    private int currntIndex = 0;
    private long time = 0;

    @Override
    protected void initData() {
        naviList = new ArrayList<>();
        NavigationBean fargment0 = new NavigationBean(FragmentNotice.class, "每日谨记", R.drawable.ic_dashboard, 0);
        NavigationBean fargment1 = new NavigationBean(FragmentPassword.class, "账户管理", R.drawable.ic_event, 1);
        NavigationBean fargment2 = new NavigationBean(FragmentNote.class, "记事本", R.drawable.ic_forum, 2);
        NavigationBean fargment3 = new NavigationBean(FragmentProgress.class, "进度记录", R.drawable.ic_forum, 3);
        NavigationBean fargment4 = new NavigationBean(FragmentHome.class, "保证书", R.drawable.ic_forum, 4);

        naviList.add(fargment0);
        naviList.add(fargment1);
        naviList.add(fargment2);
        naviList.add(fargment3);
        naviList.add(fargment4);
        naviAdapter = new NavigationAdapter(this, naviList);
    }

    @Override
    protected int getActivityContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected String getTitleText() {
        return "每日谨记";
    }

    @Override
    protected void findViewByIds() {
        //toggle 开关 http://www.it165.net/pro/html/201507/46841.html
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        findNaviViewByIds();
    }

    private void findNaviViewByIds() {
        SpacesItemDecoration vDecoration = new SpacesItemDecoration(this, LinearLayoutManager.VERTICAL);
        LinearLayoutManager popupManager = new LinearLayoutManager(this);
        popupManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(popupManager);
        mRecyclerView.addItemDecoration(vDecoration);
        mRecyclerView.setAdapter(naviAdapter);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, Fragment.instantiate(this, naviList.get(0).fragmentClass.getName()))
//                .addToBackStack(null)
                .commit();
        currntIndex = 0;
    }

    @Override
    protected void requestService() {

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
        } else {
            if (time == 0) {
                time = System.currentTimeMillis();
                showToast("再按一次返回退出！");
            } else {
                long lastTime = System.currentTimeMillis();
                if (lastTime - time < 2000) {
                    super.onBackPressed();
                } else {
                    time = lastTime;
                    showToast("再按一次返回退出！");
                }
            }
        }
    }

    @Override
    protected int getMenuInflaterRes() {
        return 0;
    }

    @Override
    protected void onMyDestory() {

    }

    @Subscriber(tag = "onNaviItemSelect")
    public void onNaviItemSelect(int position) {
        if (currntIndex != position) {
            currntIndex = position;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_content, Fragment.instantiate(this, naviList.get(position).fragmentClass.getName()))
                    .commit();
            text_title.setText(naviList.get(position).title);
        }
        mDrawerLayout.closeDrawers();
    }

}
