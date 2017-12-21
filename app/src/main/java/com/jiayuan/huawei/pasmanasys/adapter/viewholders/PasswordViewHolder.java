package com.jiayuan.huawei.pasmanasys.adapter.viewholders;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.jiayuan.huawei.hwframeworklib.adapter.MyBaseRecyclerViewAdapter;
import com.jiayuan.huawei.hwframeworklib.adapter.viewholder.BaseRViewHolder;
import com.jiayuan.huawei.hwframeworklib.utis.DateFormUtils;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.persistData.comDb.model.PasswordBean;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.OrmPswword;

import org.simple.eventbus.EventBus;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class PasswordViewHolder extends BaseRViewHolder<OrmPswword> {
    private TextView text_title;
    private TextView text_userName;
    private TextView text_account;
    private TextView text_date;

    public PasswordViewHolder(MyBaseRecyclerViewAdapter adapter, View itemView) {
        super(adapter, itemView);
    }

    @Override
    protected void findViewByIds() {
        text_title = findViewById(R.id.text_title);
        text_userName = findViewById(R.id.text_userName);
        text_account = findViewById(R.id.text_account);
        text_date = findViewById(R.id.text_date);
    }

    @Override
    protected void setItemData() {
        text_title.setText( bean.where);
        text_userName.setText( bean.userName);
        text_account.setText(bean.account);
        text_date.setText(DateFormUtils.formatDatebyTime(+bean.modifyDate));
    }

    @Override
    protected void onItemClick(int position, OrmPswword bean, View view) {
        EventBus.getDefault().post(position, "onPasswordItemClick");
    }

    @Override
    protected void onLongItemClick(int position, OrmPswword bean, View view) {
        EventBus.getDefault().post(position, "onLongPasswordItemClick");
    }
}
