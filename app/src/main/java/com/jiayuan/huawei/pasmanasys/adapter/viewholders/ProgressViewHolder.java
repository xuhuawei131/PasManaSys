package com.jiayuan.huawei.pasmanasys.adapter.viewholders;

import android.view.View;
import android.widget.TextView;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.hwframeworklib.adapter.viewholder.BaseRViewHolder;
import com.jiayuan.huawei.hwframeworklib.utis.DateFormUtils;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.ProgressBean;

import org.simple.eventbus.EventBus;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class ProgressViewHolder extends BaseRViewHolder<ProgressBean> {
    private TextView text_title;
    private TextView text_content;
    private TextView text_date;
    public ProgressViewHolder(MyBaseRecyclerViewAdapter adapter, View itemView) {
        super(adapter, itemView);
    }

    @Override
    protected void findViewByIds() {
        text_title=findViewById(R.id.text_title);
        text_content=findViewById(R.id.text_content);
        text_date=findViewById(R.id.text_date);
    }

    @Override
    protected void setItemData() {
        text_title.setText(bean.where);
        text_content.setText(bean.userName+"-"+bean.progress);
        text_date.setText(DateFormUtils.formatDatebyTime(bean.modifyDate));
    }

    @Override
    protected void onItemClick(int position, ProgressBean bean, View view) {
        EventBus.getDefault().post(position,"onProgressItemClick");
    }

    @Override
    protected void onLongItemClick(int position, ProgressBean bean, View view) {
        EventBus.getDefault().post(position,"onLongProgressItemClick");
    }
}
