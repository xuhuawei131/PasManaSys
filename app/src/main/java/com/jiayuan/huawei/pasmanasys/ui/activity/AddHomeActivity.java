package com.jiayuan.huawei.pasmanasys.ui.activity;


import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.jiayuan.huawei.hwframeworklib.ui.activity.HWBaseWithTitleActivity;
import com.jiayuan.huawei.hwframeworklib.ui.customview.keyboards.KeyboardWatcher;
import com.jiayuan.huawei.hwframeworklib.utis.DensityUtil;
import com.jiayuan.huawei.hwframeworklib.utis.HWUtils;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao.HomeDao;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao.NoteDao;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.HomeBean;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.NoteLife;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class AddHomeActivity extends HWBaseWithTitleActivity implements View.OnClickListener, KeyboardWatcher.OnKeyboardToggleListener {
    private HomeBean bean = null;

    @Bind(R.id.layout_tools)
    LinearLayout layout_tools;

    @Bind(R.id.edit_contnet)
    EditText edit_contnet;

    @Bind(R.id.scrollView)
    ScrollView scrollView;

    private KeyboardWatcher keyboardWatcher;
    private Animation[] animations;

    @Override
    protected void initData() {
        bean = getIntentSeriParam("param");
        animations = new Animation[]{AnimationUtils.loadAnimation(this, R.anim.edit_fade_in),
                AnimationUtils.loadAnimation(this, R.anim.edit_fade_out)};
        animations[0].setDuration(500);
    }

    @Override
    protected String getTitleText() {
        if (bean != null) {
            return "修改保证";
        } else {
            return "添加保证";
        }
    }

    @Override
    protected int getMenuInflaterRes() {
        return R.menu.menu_ok;
    }

    @Override
    protected int getActivityContentLayout() {
        return R.layout.activity_add_home;
    }

    @Override
    protected void findViewByIds() {
        keyboardWatcher = new KeyboardWatcher(this);
        keyboardWatcher.setListener(this);
        edit_contnet.setOnClickListener(this);

        if (bean != null) {
            edit_contnet.setText(bean.content);
        }
    }

    @Override
    protected void requestService() {

    }

    @Override
    protected void onMyDestory() {
        keyboardWatcher.destroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            String content = edit_contnet.getText().toString();
            if (bean != null) {
                if (HWUtils.isEmpty(content)) {
                    HomeDao.getInstance().deleteHome(bean);
                } else {
                    bean.content = content;
                    bean.modifyDate = System.currentTimeMillis();
                    HomeDao.getInstance().updateHome(bean);
                }
                setResult(1);
                finish();
            } else {
                if (HWUtils.isEmpty(content)) {
                    showToast("内容不能为空！");
                    return true;
                }
                bean = new HomeBean();
                long date = System.currentTimeMillis();
                bean.title=content;
                bean.content = content;
                bean.createDate = date;
                bean.modifyDate = date;
                HomeDao.getInstance().add(bean);
                showToast("添加成功！");
                setResult(1);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onKeyboardShown(int keyboardSize) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
        lp.bottomMargin = DensityUtil.dip2px(55);
        scrollView.setLayoutParams(lp);

        Observable.just("").observeOn(Schedulers.io()).delay(2500, TimeUnit.MICROSECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                layout_tools.setVisibility(View.VISIBLE);
                layout_tools.startAnimation(animations[0]);
            }
        });
    }

    @Override
    public void onKeyboardClosed() {
        edit_contnet.clearFocus();
        layout_tools.setVisibility(View.GONE);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
        lp.bottomMargin = DensityUtil.dip2px(0);
        scrollView.setLayoutParams(lp);
    }
}
