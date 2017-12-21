package com.jiayuan.huawei.pasmanasys.adapter.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.hwframeworklib.adapter.viewholder.BaseRViewHolder;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.bean.NavigationBean;

import org.simple.eventbus.EventBus;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class NavigationViewHolder extends BaseRViewHolder<NavigationBean> {

    private ImageView image_icon;
    private TextView text_title;

    public NavigationViewHolder(MyBaseRecyclerViewAdapter adapter, View itemView) {
        super(adapter, itemView);
    }

    @Override
    protected void findViewByIds() {
        image_icon = findViewById(R.id.image_icon);
        text_title = findViewById(R.id.text_title);
    }

    @Override
    protected void setItemData() {
        image_icon.setImageResource(bean.iconIndex);
        text_title.setText(bean.title);
    }

    @Override
    protected void onItemClick(int position, NavigationBean bean, View view) {
        EventBus.getDefault().post(position,"onNaviItemSelect");
    }

    @Override
    protected void onLongItemClick(int position, NavigationBean bean, View view) {

    }
}
