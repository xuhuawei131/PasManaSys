package com.jiayuan.huawei.pasmanasys.adapter;

import android.view.ViewGroup;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.adapter.viewholders.NoteViewHolder;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.NoteLife;
import com.jiayuan.huawei.pasmanasys.ui.fragments.FragmentNote;

import java.util.List;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class NoteAdapter extends MyBaseRecyclerViewAdapter<NoteViewHolder,NoteLife,FragmentNote> {
    public NoteAdapter(FragmentNote context, List<NoteLife> arrayList) {
        super(context, arrayList);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteViewHolder(this,inflateView(R.layout.adapter_note,parent));
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.setData(position,arrayList.get(position));
    }
}
