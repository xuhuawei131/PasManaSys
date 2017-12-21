package com.jiayuan.huawei.hwframeworklib.ui.customview.emptyRecyclerView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jiayuan.huawei.hwframeworklib.R;
import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.JRefreshLayout;
import com.jiayuan.huawei.hwframeworklib.ui.customview.refreshLayout.PullToRefreshView;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class EmptyRecyclerViewLayout extends FrameLayout implements PullToRefreshView.OnRefreshListener{
    public EmptyRecyclerView recyclerView;
    public JRefreshLayout mJRefreshLayout;
    public LinearLayout emptyView;
    public EmptyRecyclerViewLayout(Context context) {
        this(context,null);
    }

    public EmptyRecyclerViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EmptyRecyclerViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_refresh_recycler_empty,this,true);
        this.addView(view);

        recyclerView=(EmptyRecyclerView)findViewById(R.id.mRecyclerView);
        mJRefreshLayout=(JRefreshLayout)findViewById(R.id.mJRefreshLayout);
        emptyView=(LinearLayout)findViewById(R.id.emptyView);

        if (mJRefreshLayout != null) {
            mJRefreshLayout.setOnRefreshListener(this);
        }
    }

    @Override
    public void onRefresh() {

    }
}
