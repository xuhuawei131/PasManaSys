package com.jiayuan.huawei.pasmanasys.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.viewholders.NavigationViewHolder;
import com.jiayuan.huawei.pasmanasys.bean.NavigationBean;
import com.jiayuan.huawei.pasmanasys.ui.activity.MainActivity;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class NavigationAdapter extends MyBaseRecyclerViewAdapter<NavigationViewHolder, NavigationBean, MainActivity> {
    public NavigationAdapter(MainActivity context, List<NavigationBean> arrayList) {
        super(context, arrayList);
    }

    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateView(R.layout.adapter_navi, parent);
        NavigationViewHolder holder = new NavigationViewHolder(this, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NavigationViewHolder holder, int position) {
        holder.setData(position, arrayList.get(position));
    }
}
