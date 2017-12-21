package com.jiayuan.huawei.hwframeworklib.utis;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiayuan.huawei.hwframeworklib.BaseApp;
import com.jiayuan.huawei.hwframeworklib.R;
import com.jiayuan.huawei.hwframeworklib.constants.HWConstants;


public class ToastUtil {

    public static void showActionResalt(int strID) {
        View v = View.inflate(BaseApp.context,
                R.layout.action_resalt_layout, null);
        float w = HWConstants.WIN_WIDTH;
        v.setMinimumWidth((int) (w * 0.4f));
        v.setMinimumHeight((int) (w * 0.4f));
        TextView tv = (TextView) v.findViewById(R.id.txt_1);
        tv.setText(BaseApp.context.getString(strID));
        Toast toast = new Toast(BaseApp.context);
        toast.setView(v);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();

    }

    public static void showActionResult(String str, boolean isOk) {
        View v = View.inflate(BaseApp.context,
                R.layout.action_resalt_layout, null);
        float w = HWConstants.WIN_WIDTH;
        v.setMinimumWidth((int) (w * 0.4f));
        v.setMinimumHeight((int) (w * 0.4f));
        ImageView iv = (ImageView) v.findViewById(R.id.img_1);
        iv.setImageResource(isOk ? R.drawable.action_ok
                : R.drawable.action_error);
        TextView tv = (TextView) v.findViewById(R.id.txt_1);
        tv.setText(str);
        Toast toast = new Toast(BaseApp.context);
        toast.setView(v);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();

    }

    /**
     * 签到的模态消息提示层
     *
     * @param str
     */
    public static void showSigninReslt(String str) {
        View v = View.inflate(BaseApp.context,
                R.layout.dialog_signin_success, null);
        float w = HWConstants.WIN_WIDTH;
        v.setMinimumWidth((int) (w * 0.4f));
        v.setMinimumHeight((int) (w * 0.4f));
        TextView tv = (TextView) v.findViewById(R.id.loading_tv_content);
        tv.setText(str);
        Toast toast = new Toast(BaseApp.context);
        toast.setView(v);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();

    }

    public static void showActionResult(int res, boolean isOk) {
        showActionResult(BaseApp.context.getString(res), isOk);
    }
}
