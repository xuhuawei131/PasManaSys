package com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout;

import android.content.Context;
import android.util.AttributeSet;

import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.drawable.JRefreshDrawable4;


/**
 * Created by liuxinxin on 16/4/14.
 */
public class JRefreshLayout extends PullToRefreshView {


    public JRefreshLayout(Context context) {
        super(context);
        setRefreshStyle(new JRefreshDrawable4(context, this));
    }

    public JRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRefreshStyle(new JRefreshDrawable4(context, this));
    }

}
