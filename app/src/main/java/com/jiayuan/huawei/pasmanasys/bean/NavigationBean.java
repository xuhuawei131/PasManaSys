package com.jiayuan.huawei.pasmanasys.bean;

import android.support.v4.app.Fragment;

import java.io.Serializable;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class NavigationBean implements Serializable {
    public Class fragmentClass;
    public String title;
    public int index;
    public boolean isSelected = false;
    public int iconIndex;

    public NavigationBean(Class fragmentClass, String title,int iconIndex, int index) {
        this.fragmentClass = fragmentClass;
        this.title = title;
        this.iconIndex=iconIndex;
        this.index = index;
    }
}
