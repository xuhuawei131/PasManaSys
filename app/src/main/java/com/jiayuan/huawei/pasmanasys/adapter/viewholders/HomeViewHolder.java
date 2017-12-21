package com.jiayuan.huawei.pasmanasys.adapter.viewholders;

import android.view.View;
import android.widget.TextView;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.hwframeworklib.adapter.viewholder.BaseRViewHolder;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.HomeBean;

import org.simple.eventbus.EventBus;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class HomeViewHolder extends BaseRViewHolder<HomeBean> {
    private TextView text_title;

    public HomeViewHolder(MyBaseRecyclerViewAdapter adapter, View itemView) {
        super(adapter, itemView);
    }

    @Override
    protected void findViewByIds() {
        text_title = findViewById(R.id.text_title);
    }

    @Override
    protected void setItemData() {
        text_title.setText(bean.content);
    }

    @Override
    protected void onItemClick(int position, HomeBean bean, View view) {
        EventBus.getDefault().post(position,"onHomeItemClick");
    }

    @Override
    protected void onLongItemClick(int position, HomeBean bean, View view) {
        EventBus.getDefault().post(position,"onLongHomeItemClick");
    }
}
