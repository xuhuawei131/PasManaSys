package com.jiayuan.huawei.pasmanasys.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.viewholders.PasswordViewHolder;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.OrmPswword;
import com.jiayuan.huawei.pasmanasys.ui.fragments.FragmentPassword;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class PasswordAdapter extends MyBaseRecyclerViewAdapter<PasswordViewHolder,OrmPswword,FragmentPassword> {
    public PasswordAdapter(FragmentPassword context, List<OrmPswword> arrayList) {
        super(context, arrayList);
    }

    @Override
    public PasswordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflateView(R.layout.adapter_password,parent);
        return new PasswordViewHolder(this,view);
    }

    @Override
    public void onBindViewHolder(PasswordViewHolder holder, int position) {
        holder.setData(position,arrayList.get(position));
    }
}
