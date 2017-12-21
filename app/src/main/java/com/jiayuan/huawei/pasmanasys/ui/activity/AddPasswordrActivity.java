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
import com.jiayuan.huawei.pasmanasys.Utils.SecurityUtil;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.dao.PasswordDao;
import com.jiayuan.huawei.pasmanasys.persistData.ormDb.model.OrmPswword;

import butterknife.Bind;

public class AddPasswordrActivity extends HWBaseWithTitleActivity implements View.OnClickListener {
    private OrmPswword bean;
    @Bind(R.id.input_where)
    TextInputLayout input_where;
    @Bind(R.id.edit_where)
    EditText edit_where;

    @Bind(R.id.input_userName)
    TextInputLayout input_userName;
    @Bind(R.id.edit_userName)
    EditText edit_userName;

    @Bind(R.id.input_account)
    TextInputLayout input_account;
    @Bind(R.id.edit_account)
    EditText edit_account;

    @Bind(R.id.input_password)
    TextInputLayout input_password;

    @Bind(R.id.edit_password)
    EditText edit_password;

    @Bind(R.id.input_more)
    TextInputLayout input_more;

    @Bind(R.id.edit_more)
    EditText edit_more;

    @Bind(R.id.btn_ok)
    Button btn_ok;

    private String items[] = {"密码地址名称", "密码所属人", "密码账户", "密码值", "备注"};

    @Override
    protected void initData() {
        bean = getIntentSeriParam("param");
    }

    @Override
    protected String getTitleText() {
        if (bean == null) {
            return "添加新密码";
        } else {
            return "修改密码";
        }
    }

    @Override
    protected int getActivityContentLayout() {
        return R.layout.activity_add_password;
    }

    @Override
    protected void findViewByIds() {
        input_where.setHint(items[0]);
        input_userName.setHint(items[1]);
        input_account.setHint(items[2]);
        input_password.setHint(items[3]);
        input_more.setHint(items[4]);

        btn_ok.setOnClickListener(this);

        if (bean != null) {
            bean.password = SecurityUtil.decodeAES(bean.password);
            edit_where.setText(bean.where);
            edit_userName.setText(bean.userName);
            edit_account.setText(bean.account);
            edit_password.setText(bean.password);
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
    protected int getMenuInflaterRes() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        String where = edit_where.getText().toString();
        String usserName = edit_userName.getText().toString();
        String account = edit_account.getText().toString();
        String password = edit_password.getText().toString();
        String more = edit_more.getText().toString();


        EditCheckBean beanWhere = new EditCheckBean(where, items[0]);
        EditCheckBean beanUser = new EditCheckBean(usserName, items[1]);
        EditCheckBean beanAccount = new EditCheckBean(account, items[2]);
        EditCheckBean beanPassword = new EditCheckBean(password, items[3]);

        boolean isChecked = HWUtils.EditTextEmpty(beanWhere, beanUser, beanAccount, beanPassword);
        if (isChecked) {
            if (bean == null) {
                bean = new OrmPswword();
                bean.where = where;
                bean.account = account;
                bean.content = account;
                bean.userName = usserName;
                bean.password = SecurityUtil.encodeAES(password);
                long createDate = System.currentTimeMillis();
                bean.createDate = createDate;
                bean.modifyDate = createDate;
                bean.more = more;
                PasswordDao.getInstance().add(bean);
                ToastUtil.showActionResult("添加成功", true);
                setResult(1);
                finish();
            } else {
                bean.where = where;
                bean.account = account;
                bean.content = account;
                bean.userName = usserName;
                bean.password = SecurityUtil.encodeAES(password);
                bean.more = more;
                long createDate = System.currentTimeMillis();
                bean.modifyDate = createDate;
                PasswordDao.getInstance().updateOrmPswword(bean);
                ToastUtil.showActionResult("修改成功", true);
                setResult(1);
                finish();
            }
        }
    }
}
