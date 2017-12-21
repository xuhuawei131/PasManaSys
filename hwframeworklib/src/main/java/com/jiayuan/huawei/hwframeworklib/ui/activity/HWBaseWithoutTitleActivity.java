package com.jiayuan.huawei.hwframeworklib.ui.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anthonycr.grant.PermissionsManager;
import com.jiayuan.huawei.hwframeworklib.BaseApp;
import com.jiayuan.huawei.hwframeworklib.R;

import butterknife.ButterKnife;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public  abstract class HWBaseWithoutTitleActivity extends BaseActivity {
    protected int getChildActivityContentLayout() {
        return R.layout.activity_base;
    }
    protected void findChildViewByIds() {

    }
}
