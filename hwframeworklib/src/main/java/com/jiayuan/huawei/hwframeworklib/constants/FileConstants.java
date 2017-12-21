package com.jiayuan.huawei.hwframeworklib.constants;

import android.os.Environment;

import java.io.File;

/**
 * $desc$
 * 文件常量
 * @author xuhuawei
 * @time $date$ $time$
 */
public class FileConstants {
    private static final String ROOT = "/"+HWConstants.getFileRootName()+"/";
    private static String AUDIO_DIR = null;
    private static String IMAGE_DIR;
    private static String THUMB_IMAGE_DIR;
    private static String VIDEO_DIR;
    private static String EMOJI_DIR;
    private static String ZIP_DIR;
    private static String BUBBLE;
    private static String EXCEL;
    private static String APK;
    private static String DATABASE;

    private static File getRootDir() {
        File rootFile;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            rootFile = Environment.getExternalStorageDirectory();// 获取跟目录
        } else {
            rootFile = new File("/");
        }
        return rootFile;
    }

    public static File getImageDir() {
        if (IMAGE_DIR == null) {
            IMAGE_DIR = getRootDir() + ROOT + "/image";
        }
        File file = new File(IMAGE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getThumbImageDir() {
        if (THUMB_IMAGE_DIR == null) {
            THUMB_IMAGE_DIR = getRootDir() + ROOT + "/image/thumb";
        }
        File file = new File(THUMB_IMAGE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    public static File getAudioDir() {
        if (AUDIO_DIR == null) {
            AUDIO_DIR = getRootDir() + ROOT + "/audio";
        }
        File file = new File(AUDIO_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getVideoDir() {
        if (VIDEO_DIR == null) {
            VIDEO_DIR = getRootDir() + ROOT + "/video";
        }
        File file = new File(VIDEO_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getEmojiDir() {
        if (EMOJI_DIR == null) {
            EMOJI_DIR = getRootDir() + ROOT + "/express";
        }
        File file = new File(EMOJI_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getZipDir() {
        if (ZIP_DIR == null) {
            ZIP_DIR = getRootDir() + ROOT + "/zip";
        }
        File file = new File(ZIP_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
    public static File getExcelDir() {
        if (EXCEL == null) {
            EXCEL = getRootDir() + ROOT + "/excel";
        }
        File file = new File(EXCEL);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 数据库
     * @return
     */
    public static File getDataBaseDir() {
        if (DATABASE == null) {
            DATABASE = getRootDir() + ROOT + "/database";
        }
        File file = new File(DATABASE);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
    public static File getApkDir() {
        if (APK == null) {
            APK = getRootDir() + ROOT + "/apk";
        }
        File file = new File(APK);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

}
