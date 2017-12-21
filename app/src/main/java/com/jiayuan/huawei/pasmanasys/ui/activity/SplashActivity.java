package com.jiayuan.huawei.pasmanasys.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.jiayuan.huawei.hwframeworklib.ui.activity.HWBaseWithoutTitleActivity;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.Utils.RandomUtils;

import butterknife.Bind;

public class SplashActivity extends HWBaseWithoutTitleActivity implements View.OnClickListener, Animation.AnimationListener {
    //显示图片的ImageView组件
    @Bind(R.id.image_splash)
    ImageView image_splash;
    //要展示的一组图片资源
    private int[] pictures;
    //每张展示图片要执行的一组动画效果
    private Animation[] animations;
    //当前执行的是第几张图片（资源索引）
    private int currentItem = 0;
    private int indexAnim = 0;

    @Override
    protected void initData() {
        translateType = 0;
        currentItem = RandomUtils.getRandomNum(0, 4);
        //实例化引导图片数组
        pictures = new int[]{(R.drawable.sp1), (R.drawable.sp2),
                (R.drawable.sp3), (R.drawable.sp4), (R.drawable.sp5)};
        //实例化动画效果数组
        animations = new Animation[]{AnimationUtils.loadAnimation(this, R.anim.guide_fade_in),
                AnimationUtils.loadAnimation(this, R.anim.guide_fade_in_scale),
                AnimationUtils.loadAnimation(this, R.anim.guide_fade_out),
                AnimationUtils.loadAnimation(this, R.anim.guide_fade_in_and_scale)
        };

        //给每个动画效果设置播放时间
        animations[0].setDuration(3000);
        animations[1].setDuration(3000);
        animations[2].setDuration(3000);
        animations[3].setDuration(3000);

        //给每个动画效果设置监听事件
        animations[0].setAnimationListener(this);
        animations[1].setAnimationListener(this);
        animations[2].setAnimationListener(this);
        animations[3].setAnimationListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected int getActivityContentLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void findViewByIds() {
        image_splash.setOnClickListener(this);
    }

    @Override
    protected void requestService() {
        image_splash.setImageResource(pictures[currentItem]);
        image_splash.startAnimation(animations[3]);
    }

    @Override
    protected void onMyDestory() {

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    @Override
    public void onClick(View v) {
//        currentItem++;
//        indexAnim++;
//        showToast("当前动画的编号是："+indexAnim%animations.length);
//        image_splash.setImageResource(pictures[currentItem%pictures.length]);
//        image_splash.startAnimation(animations[indexAnim%animations.length]);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
