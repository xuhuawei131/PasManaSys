package com.jiayuan.huawei.pasmanasys;

import com.jiayuan.huawei.hwframeworklib.BaseApp;
import com.jiayuan.huawei.hwframeworklib.persistData.commdb.database.MySQLiteDatabase;
import com.jiayuan.huawei.pasmanasys.persistData.comDb.helper.AppPhoneManagerSysHelper;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class MyApp extends BaseApp {

    @Override
    protected String getFileRootName() {
        return "CodeManager";
    }

    @Override
    protected void initData() {
//        MySQLiteDatabase.getInstance().initDataBase(new AppPhoneManagerSysHelper(),null);
    }
}
