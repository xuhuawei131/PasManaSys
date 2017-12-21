package com.jiayuan.huawei.pasmanasys.adapter;

import android.view.ViewGroup;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.viewholders.HomeViewHolder;
import com.jiayuan.huawei.pasmanasys.adapter.viewholders.NoticeViewHolder;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.HomeBean;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.NoticeBean;
import com.jiayuan.huawei.pasmanasys.ui.fragments.FragmentHome;
import com.jiayuan.huawei.pasmanasys.ui.fragments.FragmentNotice;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class NoticeAdapter extends MyBaseRecyclerViewAdapter<NoticeViewHolder, NoticeBean, FragmentNotice> {
    public NoticeAdapter(FragmentNotice context, List<NoticeBean> arrayList) {
        super(context, arrayList);
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoticeViewHolder(this, inflateView(R.layout.adapter_notice, parent));
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, int position) {
        holder.setData(position, arrayList.get(position));
    }
}
