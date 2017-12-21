package com.jiayuan.huawei.hwframeworklib.ui.activity;


import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jiayuan.huawei.hwframeworklib.R;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class HWBaseWithTitleActivity extends BaseActivity {
    protected Toolbar toolbar;
    protected AppBarLayout mAppBarLayout;
    protected TextView text_title;

    protected abstract String getTitleText();

    protected abstract int getMenuInflaterRes();

    protected int getChildActivityContentLayout() {
        return R.layout.activity_base_title;
    }

    protected void findChildViewByIds() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        text_title = (TextView) findViewById(R.id.text_title);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.mAppBarLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        String title = getTitleText();
        text_title.setText(title);

        if (toolbar == null) {
            return;
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int res = getMenuInflaterRes();
        if (res != 0) {
            getMenuInflater().inflate(res, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

}
