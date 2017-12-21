package com.jiayuan.huawei.pasmanasys.persistData.comDb.helper;


import android.support.annotation.NonNull;

import com.jiayuan.huawei.hwframeworklib.persistData.commdb.database.DataBaseConfig;
import com.jiayuan.huawei.hwframeworklib.persistData.commdb.helper.BasePhoneManagerSysHepler;
import com.jiayuan.huawei.pasmanasys.MyApp;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class SDPhoneManagerSysHelper extends BasePhoneManagerSysHepler  {
    public SDPhoneManagerSysHelper(@NonNull DataBaseConfig config) {
        super(MyApp.context, config);
    }
}
