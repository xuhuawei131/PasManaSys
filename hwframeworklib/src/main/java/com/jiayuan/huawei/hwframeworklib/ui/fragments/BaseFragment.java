package com.jiayuan.huawei.hwframeworklib.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jiayuan.huawei.hwframeworklib.R;
import com.jiayuan.huawei.hwframeworklib.ui.activity.BaseActivity;
import com.jiayuan.huawei.hwframeworklib.ui.interfaces.MyBaseContext;

import org.simple.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class BaseFragment extends Fragment implements MyBaseContext{
    protected BaseActivity context;
    protected View fragmentView;

    protected abstract void initData(Bundle bundle);

    protected abstract int getFragmentLayout();

    protected abstract void findViewByIds();

    protected abstract void requestSerivce();

    protected abstract void onMyDestory();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivity activity = (BaseActivity) this.getActivity();
        Bundle bundle = this.getArguments();
        context = activity;
        initData(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (fragmentView == null) {
            fragmentView = inflater.inflate(R.layout.fragment_base, null);
            ButterKnife.bind(fragmentView);
            EventBus.getDefault().register(this);
            FrameLayout layout_content = findViewById(R.id.layout_main_content);
            int res = getFragmentLayout();
            if (res != 0) {
                View fragmentChildView = inflater.inflate(res, null);
                layout_content.addView(fragmentChildView);
            }
            findViewByIds();
            Observable.just("").subscribeOn(Schedulers.io())
                    .delay(2500, TimeUnit.MICROSECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            requestSerivce();
                        }
                    });

        } else {
            ViewGroup parent = (ViewGroup) fragmentView.getParent();
            if (parent != null) {
                parent.removeView(fragmentView);
            }
        }
        return fragmentView;
    }

    protected <T extends View> T findViewById(int res) {
        return (T) fragmentView.findViewById(res);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(fragmentView);
        EventBus.getDefault().unregister(this);
        onMyDestory();
    }

    protected void finish() {
        this.getActivity().finish();
    }

    public void showToast(String text) {
        Toast.makeText(this.getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int res) {
        Toast.makeText(this.getActivity(), res, Toast.LENGTH_SHORT).show();
    }
    public Context getContext(){
        return this.getActivity();
    }


    protected boolean isLoadingView() {
        return context.isLoadingView();
    }

    protected void showLoadingView() {
        showLoadingView("加载中...");
    }

    protected void showLoadingView(String str) {
        context.showLoadingView(str);
    }

    protected void disLoadingView() {
        context.isLoadingView();
    }


}
