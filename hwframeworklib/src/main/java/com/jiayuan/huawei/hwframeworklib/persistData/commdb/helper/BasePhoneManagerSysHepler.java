package com.jiayuan.huawei.hwframeworklib.persistData.commdb.helper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jiayuan.huawei.hwframeworklib.persistData.commdb.database.DataBaseConfig;
import com.jiayuan.huawei.hwframeworklib.utis.HWUtils;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class BasePhoneManagerSysHepler extends SQLiteOpenHelper {
    public DataBaseConfig config;
    public Context context;

    public BasePhoneManagerSysHepler(Context context, @NonNull DataBaseConfig config) {
        super(context, config.getDBName(), null, config.getDBVersion());
        this.context = context;
        this.config = config;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if (config.getSqlCreateArray() != null) {
            for (String sql : config.getSqlCreateArray()) {
                if(!HWUtils.isEmpty(sql)){
                    sqLiteDatabase.execSQL(sql);
                }
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        int version = oldVersion;
        if (version != config.getDBVersion() && config.getsqlTableNameArray() != null) {
            for (String tableName : config.getsqlTableNameArray()) {
                if(!HWUtils.isEmpty(tableName)){
                    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
                }
            }
            onCreate(sqLiteDatabase);
        }
    }

    public SQLiteDatabase getDataBase() {
        return this.getWritableDatabase("xuhuawei");
    }

    public void deleteDataBase() {
        context.deleteDatabase(config.getDBName());
    }

    public String getDataBasePath() {
        return context.getDatabasePath(config.getDBName()).getPath();
    }
}
