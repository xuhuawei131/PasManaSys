package com.jiayuan.huawei.hwframeworklib.utis;

import android.content.ClipboardManager;
import android.content.Context;

/**
 * $desc$
 * 粘贴板管理
 * @author xuhuawei
 * @time $date$ $time$
 */
public class ClipboardUtil {
    /**
     * 实现文本复制功能
     * add by xhw
     *
     * @param content
     */
    public static void copy(String content, Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     * add by xhw
     *
     * @param context
     * @return
     */
    public static String paste(Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }
}
