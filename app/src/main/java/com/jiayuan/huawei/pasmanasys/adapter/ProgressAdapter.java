package com.jiayuan.huawei.pasmanasys.adapter;

import android.view.ViewGroup;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.viewholders.ProgressViewHolder;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.ProgressBean;
import com.jiayuan.huawei.pasmanasys.ui.fragments.FragmentProgress;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class ProgressAdapter extends MyBaseRecyclerViewAdapter<ProgressViewHolder, ProgressBean, FragmentProgress> {

    public ProgressAdapter(FragmentProgress context, List<ProgressBean> arrayList) {
        super(context, arrayList);
    }

    @Override
    public ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProgressViewHolder(this, inflateView(R.layout.adapter_progress, parent));
    }

    @Override
    public void onBindViewHolder(ProgressViewHolder holder, int position) {
        holder.setData(position, arrayList.get(position));
    }
}
