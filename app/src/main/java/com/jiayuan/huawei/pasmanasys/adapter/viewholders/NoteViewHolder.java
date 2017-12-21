package com.jiayuan.huawei.pasmanasys.adapter.viewholders;

import android.view.View;
import android.widget.TextView;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.hwframeworklib.adapter.viewholder.BaseRViewHolder;
import com.jiayuan.huawei.hwframeworklib.ui.customview.mutiTexts.SolidTextView;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.NoteLife;

import org.simple.eventbus.EventBus;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class NoteViewHolder extends BaseRViewHolder<NoteLife> {

    private TextView text_title;
    private SolidTextView text_content;
    public NoteViewHolder(MyBaseRecyclerViewAdapter adapter, View itemView) {
        super(adapter, itemView);
    }

    @Override
    protected void findViewByIds() {
        text_title=findViewById(R.id.text_title);
        text_content=findViewById(R.id.text_content);
    }

    @Override
    protected void setItemData() {
        text_title.setText(bean.content);
        text_content.setText(bean.content);
    }

    @Override
    protected void onItemClick(int position, NoteLife bean, View view) {
        EventBus.getDefault().post(position, "onNoteItemClick");
    }

    @Override
    protected void onLongItemClick(int position, NoteLife bean, View view) {
        EventBus.getDefault().post(position, "onLongNoteItemClick");
    }
}
