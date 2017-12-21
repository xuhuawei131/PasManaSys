package com.jiayuan.huawei.pasmanasys.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jiayuan.huawei.hwframeworklib.beans.EditCheckBean;
import com.jiayuan.huawei.hwframeworklib.ui.activity.HWBaseWithTitleActivity;
import com.jiayuan.huawei.hwframeworklib.utis.HWUtils;
import com.jiayuan.huawei.hwframeworklib.utis.ToastUtil;
import com.jiayuan.huawei.pasmanasys.R;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao.ProgressDao;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.ProgressBean;

import butterknife.Bind;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class AddProgressActivity extends HWBaseWithTitleActivity implements View.OnClickListener {
    private ProgressBean bean;

    @Bind(R.id.input_where)
    TextInputLayout input_where;
    @Bind(R.id.edit_where)
    EditText edit_where;

    @Bind(R.id.input_userName)
    TextInputLayout input_userName;
    @Bind(R.id.edit_userName)
    EditText edit_userName;

    @Bind(R.id.input_progress)
    TextInputLayout input_progress;
    @Bind(R.id.edit_progress)
    EditText edit_progress;

    @Bind(R.id.input_more)
    TextInputLayout input_more;
    @Bind(R.id.edit_more)
    EditText edit_more;

    @Bind(R.id.btn_ok)
    Button btn_ok;

    private String items[] = {"项目名称", "项目所属人", "项目进度", "备注"};

    @Override
    protected void initData() {
        bean = getIntentSeriParam("param");
    }

    @Override
    protected String getTitleText() {
        if (bean != null) {
            return "修改进度";
        } else {
            return "添加进度";
        }
    }

    @Override
    protected int getMenuInflaterRes() {
        return 0;
    }


    @Override
    protected int getActivityContentLayout() {
        return R.layout.activity_add_progress;
    }

    @Override
    protected void findViewByIds() {
        input_where.setHint(items[0]);
        input_userName.setHint(items[1]);
        input_progress.setHint(items[2]);
        input_more.setHint(items[3]);

        btn_ok.setOnClickListener(this);
        if (bean != null) {
            edit_where.setText(bean.where);
            edit_userName.setText(bean.userName);
            edit_progress.setText(bean.progress);
            edit_more.setText(bean.more);
        }
    }

    @Override
    protected void requestService() {

    }

    @Override
    protected void onMyDestory() {

    }

    @Override
    public void onClick(View v) {
        String where = edit_where.getText().toString();
        String usserName = edit_userName.getText().toString();
        String progress = edit_progress.getText().toString();
        String more = edit_more.getText().toString();


        EditCheckBean beanWhere = new EditCheckBean(where, items[0]);
        EditCheckBean beanUser = new EditCheckBean(usserName, items[1]);
        EditCheckBean beanAccount = new EditCheckBean(progress, items[2]);

        boolean isChecked = HWUtils.EditTextEmpty(beanWhere, beanUser, beanAccount);
        if (isChecked) {
            if (bean == null) {
                bean = new ProgressBean();
                bean.where = where;
                bean.userName = usserName;
                bean.progress = progress;
                bean.content = progress;
                long createDate = System.currentTimeMillis();
                bean.createDate = createDate;
                bean.modifyDate = createDate;
                bean.more = more;
                ProgressDao.getInstance().add(bean);
                ToastUtil.showActionResult("添加成功", true);
                setResult(1);
                finish();
            } else {
                bean.where = where;
                bean.progress = progress;
                bean.userName = usserName;
                bean.more = more;
                bean.content = progress;
                long createDate = System.currentTimeMillis();
                bean.modifyDate = createDate;
                ProgressDao.getInstance().updateProgress(bean);
                ToastUtil.showActionResult("修改成功", true);
                setResult(1);
                finish();
            }
        }
    }
}
