package com.jiayuan.huawei.hwframeworklib.utis;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;

import com.jiayuan.huawei.hwframeworklib.BaseApp;

import java.io.File;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class PhoneUtil {
    /**
     * 设备串码
     *
     * @return
     */
    public static String getImei() {
        TelephonyManager mTm = (TelephonyManager) BaseApp.context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTm.getDeviceId();
    }

    /**
     * 设备型号
     *
     * @return
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取设备名称
     *
     * @return
     */
    public static String getName() {
        return Build.BRAND;
    }

    /**
     * 系统版本代码
     */
    public static int getSysVerCode() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 系统版本号
     *
     * @return
     */
    public static String getSysVerName() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 屏幕分辨率
     *
     * @return
     */
    public static DisplayMetrics getScreenWH() {
        return BaseApp.context.getResources()
                .getDisplayMetrics();
    }

    /**
     * 获取设备的尺寸
     *
     * @return
     */
    public static String getDeviceInch() {
        DisplayMetrics dm = getScreenWH();
        int density = dm.densityDpi;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        double inch = Math.sqrt(width * width + height * height) / density;
        return String.format("%.1f寸", inch);
    }

    /**
     * 获取mac地址
     *
     * @return
     */
    public static String getMacAddress() {
        WifiManager wifiManager = (WifiManager) BaseApp.context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getMacAddress();
    }

    /**
     * 获得SD卡总大小
     *
     * @return
     */
    public static String getExtendSDCardSize() {
        File path = Environment.getExternalStorageDirectory();
        long size = 0;
        StatFs stat = new StatFs(path.getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            size = stat.getTotalBytes();
        } else {
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            size = blockSize * totalBlocks;
        }
        return Formatter.formatFileSize(BaseApp.context, size);
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */
    public static String getSDAvailableSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long size = 0;
        if (Build.VERSION.SDK_INT >= 18) {
            size = stat.getAvailableBytes();
        } else {
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            size = blockSize * availableBlocks;
        }
        return Formatter.formatFileSize(BaseApp.context, size);
    }

    /**
     * 获得机身内存总大小
     *
     * @return
     */
    public static String getRomTotalSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long size = 0;
        if (Build.VERSION.SDK_INT >= 18) {
            size = stat.getTotalBytes();
        } else {
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            size = blockSize * totalBlocks;
        }
        return Formatter.formatFileSize(BaseApp.context, size);
    }

    /**
     * 获得机身可用内存
     *
     * @return
     */
    public static String getRomAvailableSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long size = 0;
        if (Build.VERSION.SDK_INT >= 18) {
            size = stat.getAvailableBytes();
        } else {
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            size = blockSize * availableBlocks;
        }
        return Formatter.formatFileSize(BaseApp.context, size);
    }
}
