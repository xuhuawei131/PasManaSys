package com.jiayuan.huawei.hwframeworklib.constants;

import com.jiayuan.huawei.hwframeworklib.utis.HWUtils;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class HWConstants {
    public static int WIN_WIDTH;
    public static int WIN_HEIGHT;

    public static final int PAGE_MAX_COUNT=50;

    private static String FILE_ROOT_NAME = null;

    public static String getFileRootName() {
        if(HWUtils.isEmpty(FILE_ROOT_NAME)){
            return "XHW";
        }
        return FILE_ROOT_NAME;
    }

    public static void setFileRootName(String fileName) {
        FILE_ROOT_NAME = fileName;
    }
}
