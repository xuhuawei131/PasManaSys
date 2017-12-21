package com.jiayuan.huawei.pasmanasys.persistData.comDb.config;

import com.jiayuan.huawei.hwframeworklib.persistData.commdb.database.DataBaseConfig;
import com.jiayuan.huawei.pasmanasys.persistData.comDb.tables.PasswordTable;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class PassWordConfig extends DataBaseConfig implements BaseConfig, PasswordTable {
    @Override
    public String setDB_NAME() {
        return DATABSE_NAME;
    }

    @Override
    public int setDB_VERSION() {
        return DATABSE_VERSION;
    }

    @Override
    public String[] setSqlCreateArray() {
        String CREATE_TABLE_PASSWORD = "CREATE TABLE " + PASS_TABLE_NAME
                + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PASS_WHERE + " TEXT, "
                + PASS_USERNAME + " TEXT, "
                + PASS_ACCOUNT + " TEXT, "
                + PASS_PASSWORD + " TEXT, "
                + MORE + " TEXT)";
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";
        return new String[]{CREATE_TABLE_PASSWORD, sql1, sql2, sql3};
    }

    @Override
    public String[] sqlTableNameArray() {
        return new String[]{PASS_TABLE_NAME};
    }
}
