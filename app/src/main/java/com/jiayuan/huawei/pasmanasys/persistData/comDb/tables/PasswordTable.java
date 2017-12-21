package com.jiayuan.huawei.pasmanasys.persistData.comDb.tables;

import com.jiayuan.huawei.hwframeworklib.persistData.commdb.BASE_TABLE;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public interface PasswordTable extends BASE_TABLE {
    public static final String PASS_TABLE_NAME = "password";
    public static final String PASS_WHERE = "where";
    public static final String PASS_USERNAME = "userName";
    public static final String PASS_ACCOUNT = "account";
    public static final String PASS_PASSWORD = "password";
}
