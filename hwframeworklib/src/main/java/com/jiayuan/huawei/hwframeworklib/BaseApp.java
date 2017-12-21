package com.jiayuan.huawei.hwframeworklib;

import android.app.Application;
import android.content.Context;

import com.jiayuan.huawei.hwframeworklib.constants.HWConstants;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class BaseApp extends Application {
    public static Context context;
    //获取文件根目录名称
    protected abstract String getFileRootName();

    //初始化数据
    protected abstract void initData();
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        String fileRootName=getFileRootName();
        HWConstants.setFileRootName(fileRootName);
        HWConstants.WIN_WIDTH = this.getResources()
                .getDisplayMetrics().widthPixels;
        HWConstants.WIN_HEIGHT = this.getResources()
                .getDisplayMetrics().heightPixels;
        initData();
    }
}
