package com.jiayuan.huawei.pasmanasys.persistData.comDb.helper;

import com.jiayuan.huawei.hwframeworklib.BaseApp;
import com.jiayuan.huawei.hwframeworklib.persistData.commdb.helper.BasePhoneManagerSysHepler;
import com.jiayuan.huawei.pasmanasys.persistData.comDb.config.PassWordConfig;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class AppPhoneManagerSysHelper extends BasePhoneManagerSysHepler  {
    public AppPhoneManagerSysHelper() {
        super(BaseApp.context, new PassWordConfig());
    }
}
