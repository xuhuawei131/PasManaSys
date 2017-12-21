package com.jiayuan.huawei.hwframeworklib.persistData.commdb.impleDao;

import android.content.ContentValues;

import com.jiayuan.huawei.hwframeworklib.persistData.commdb.database.MySQLiteDatabase;

import net.sqlcipher.Cursor;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class BaseDBImpl<T> {
    protected MySQLiteDatabase database;
    protected MySQLiteDatabase getDataBase() {
        return MySQLiteDatabase.getInstance();
    }
    public abstract long inserData(T info);

    public abstract int updateData(T info);
    public abstract boolean isExistData(T info);

    public abstract ContentValues dataBean2ContentValues(T info);
    public abstract T cursor2DataBean(Cursor cursor);

}
