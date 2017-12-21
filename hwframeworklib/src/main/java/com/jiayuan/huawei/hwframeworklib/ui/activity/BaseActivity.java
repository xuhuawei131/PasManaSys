package com.jiayuan.huawei.hwframeworklib.ui.activity;

import android.content.Context;
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

import com.jiayuan.huawei.hwframeworklib.R;
import com.jiayuan.huawei.hwframeworklib.ui.interfaces.MyBaseContext;

import org.simple.eventbus.EventBus;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class BaseActivity extends AppCompatActivity implements MyBaseContext {

    protected abstract void initData();

    protected abstract int getChildActivityContentLayout();

    protected abstract void findChildViewByIds();

    protected abstract int getActivityContentLayout();

    protected abstract void findViewByIds();

    protected abstract void requestService();

    protected abstract void onMyDestory();

    private FrameLayout layout_main_content;

    //进度条
    private LinearLayout layout_progress;
    private ImageView image_loading;
    private TextView text_loading;
    private AnimationDrawable ad;
    protected int translateType = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initData();
        if (translateType == 0) {
            overridePendingTransition(R.anim.page_anim_push_up_in, R.anim.page_anim_nothing);
        } else if (translateType == 1) {
            overridePendingTransition(R.anim.page_anim_push_down_in, R.anim.page_anim_nothing);
        } else {
            overridePendingTransition(R.anim.page_anim_right_in, R.anim.page_anim_left_out);
        }
        super.onCreate(savedInstanceState);
        initData();
        setContentView(getChildActivityContentLayout());
        findChildViewByIds();

        layout_main_content = (FrameLayout) findViewById(R.id.layout_main_content);

        image_loading = (ImageView) findViewById(R.id.img_1);
        text_loading = (TextView) findViewById(R.id.txt_1);
        layout_progress = (LinearLayout) findViewById(R.id.layout_progress);

        setModalConfig(true);

        int layoutID = getActivityContentLayout();
        if (layoutID != 0) {
            View view = LayoutInflater.from(this).inflate(layoutID, null);
            layout_main_content.addView(view);
        }
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        findViewByIds();
        requestService();
    }

    protected <T extends Serializable>T getIntentSeriParam(String param){
        return (T)this.getIntent().getSerializableExtra(param);
    }

    public <T extends View> T findMyViewById(int id) {
        return (T) layout_main_content.findViewById(id);
    }

    public boolean isLoadingView() {
        return layout_progress.getVisibility() == View.VISIBLE;
    }

    public void showLoadingView() {
        showLoadingView("加载中...");
    }

    public void showLoadingView(String str) {
        text_loading.setText(str);
        layout_progress.setVisibility(View.VISIBLE);
        ad = (AnimationDrawable) image_loading.getDrawable();
        image_loading.post(new Runnable() {
            @Override
            public void run() {
                if (ad.isRunning()) {
                    ad.stop();
                }
                ad.start();
            }
        });
    }

    public void disLoadingView() {
        layout_progress.setVisibility(View.GONE);
    }

    protected void setModalConfig(boolean isClickble) {
        if (isClickble) {
            layout_progress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout_progress.setVisibility(View.GONE);
                }
            });
        }
    }

    public void setArrowBackListener(Toolbar toolbar) {
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

    protected void showToast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
    protected void showToast(int content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        onMyDestory();
    }

    public Context getContext(){
        return this;
    }
}
