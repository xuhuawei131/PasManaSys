package com.jiayuan.huawei.hwframeworklib.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiayuan.huawei.hwframeworklib.ui.activity.BaseActivity;
import com.jiayuan.huawei.hwframeworklib.ui.interfaces.MyBaseContext;

import java.util.List;

/**
 * $desc$
 * recyclerview 的适配器
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class MyBaseRecyclerViewAdapter<T extends RecyclerView.ViewHolder, DataType, ActivityType extends MyBaseContext> extends RecyclerView.Adapter<T> {
    protected List<DataType> arrayList;
    private ActivityType context;

    public MyBaseRecyclerViewAdapter(ActivityType context, List<DataType> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public List<DataType> getArrayList() {
        return arrayList;
    }

    public ActivityType getActivity() {
        return (ActivityType) context;
    }

    @Override
    public int getItemCount() {
        int length = arrayList == null ? 0 : arrayList.size();
        Log.v("xhw","MyBaseRecyclerViewAdapter getItemCount "+length);
        return length;
    }

    protected View inflateView(int resLayout, ViewGroup parent) {
        return LayoutInflater.from(context.getContext()).inflate(resLayout, parent, false);
    }

}
