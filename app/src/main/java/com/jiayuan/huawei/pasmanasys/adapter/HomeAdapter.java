package com.jiayuan.huawei.pasmanasys.adapter;

import android.view.ViewGroup;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.viewholders.HomeViewHolder;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.HomeBean;
import com.jiayuan.huawei.pasmanasys.ui.fragments.FragmentHome;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class HomeAdapter extends MyBaseRecyclerViewAdapter<HomeViewHolder, HomeBean, FragmentHome> {
    public HomeAdapter(FragmentHome context, List<HomeBean> arrayList) {
        super(context, arrayList);
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewHolder(this, inflateView(R.layout.adapter_home, parent));
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.setData(position, arrayList.get(position));
    }
}
