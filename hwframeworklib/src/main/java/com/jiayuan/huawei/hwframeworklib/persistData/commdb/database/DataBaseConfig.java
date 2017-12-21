package com.jiayuan.huawei.hwframeworklib.persistData.commdb.database;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public abstract class DataBaseConfig {
    private String sqlCreate[];
    private String sqlTableName[];
    private String DB_NAME;
    private int DB_VERSION;

    public DataBaseConfig() {
        this.sqlCreate = setSqlCreateArray();
        this.sqlTableName = sqlTableNameArray();
        this.DB_NAME = setDB_NAME();
        this.DB_VERSION = setDB_VERSION();
    }

    public String getDBName() {
        return DB_NAME;
    }

    public int getDBVersion() {
        return DB_VERSION;
    }

    public String[] getSqlCreateArray() {
        return sqlCreate;
    }

    public String[] getsqlTableNameArray() {
        return sqlTableName;
    }


    protected abstract String setDB_NAME();

    protected abstract int setDB_VERSION();

    protected abstract String[] setSqlCreateArray();

    protected abstract String[] sqlTableNameArray();
}
