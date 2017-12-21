package com.jiayuan.huawei.hwframeworklib.persistData.commdb.database;

import android.content.ContentValues;

import com.jiayuan.huawei.hwframeworklib.persistData.commdb.helper.BasePhoneManagerSysHepler;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class MySQLiteDatabase {
    private static MySQLiteDatabase instance = null;

    private SQLiteDatabase appDataBase;
    private SQLiteDatabase sdDataBase;

    private MySQLiteDatabase() {

    }

    public static MySQLiteDatabase getInstance() {
        if (instance == null) {
            instance = new MySQLiteDatabase();
        }
        return instance;
    }

    /**
     * 初始化 数据库
     *
     * @param appHelper 放置在内存app data中的
     * @param sdHelper  放置在sd卡中的
     */
    public void initDataBase(BasePhoneManagerSysHepler appHelper, BasePhoneManagerSysHepler sdHelper) {
        appDataBase = appHelper.getDataBase();
        if (sdHelper != null) {
            sdDataBase = sdHelper.getDataBase();
        }
    }


    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        if (sdDataBase != null) {
            sdDataBase.update(table, values, whereClause, whereArgs);
        }
        return appDataBase.update(table, values, whereClause, whereArgs);
    }

    public long insert(String table, String nullColumnHack, ContentValues values) {
        if (sdDataBase != null) {
            sdDataBase.insert(table, nullColumnHack, values);
        }
        return appDataBase.insert(table, nullColumnHack, values);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        return sdDataBase.rawQuery(sql, selectionArgs);
    }

    public void closeDataBase() {
        if (appDataBase != null && appDataBase.isOpen()) {
            appDataBase.close();
            appDataBase = null;
        }
        if (sdDataBase != null && sdDataBase.isOpen()) {
            sdDataBase.close();
            sdDataBase = null;
        }
        instance = null;
    }

    public SQLiteDatabase getAppDataBase() {
        return appDataBase;
    }

    public SQLiteDatabase getSdDataBase() {
        return sdDataBase;
    }


}
