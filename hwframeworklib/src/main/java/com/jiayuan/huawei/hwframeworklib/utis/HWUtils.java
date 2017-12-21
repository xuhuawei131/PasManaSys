package com.jiayuan.huawei.hwframeworklib.utis;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.text.TextUtils;

import com.jiayuan.huawei.hwframeworklib.BaseApp;
import com.jiayuan.huawei.hwframeworklib.beans.EditCheckBean;
import com.jiayuan.huawei.hwframeworklib.constants.FileConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class HWUtils {

    /**
     * 获取指定文件的文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        int lastIndexOf = filePath.lastIndexOf("/");
        String fileName = filePath.substring(lastIndexOf + 1, filePath.length());
        return fileName;
    }

    /**
     * 分享自己的apk
     */
    public static void shareMySelfApk() {
        Context context = BaseApp.context;
        String pkName = context.getPackageName();
        shareAndSendAPK(context, pkName);
    }

    public static void shareMySelfApk(Context context) {
        String pkName = context.getPackageName();
        shareAndSendAPK(context, pkName);
    }

    public static void shareAndSendAPK(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            packageName = context.getPackageName();
        }
        String appDir = null;
        try {
            // 指定包名的程序源文件路径
            appDir = context.getPackageManager().getApplicationInfo(
                    packageName, 0).sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        appDir = "file://" + appDir;
        Uri uri = Uri.parse(appDir);
        // 发送
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("*/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "发送"));
    }

    /**
     * 发送信息
     *
     * @param context
     */
    public static void shareAndSendReceiveAPK(final Context context) {
        File file = new File(FileConstants.getApkDir(), "app.apk");

        if (!file.exists()) {
            Observable.just(file)
                    .observeOn(Schedulers.io())
                    .map(new Func1<File, String>() {
                        @Override
                        public String call(File file) {
                            return copyFile("app.apk", file);
                        }
                    }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String filePath) {
                            if (HWUtils.isEmpty(filePath)) {

                            } else {
                                String appDir = "file://" + filePath;
                                Uri uri = Uri.parse(appDir);
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.putExtra(Intent.EXTRA_STREAM, uri);
                                intent.setType("*/*");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(Intent.createChooser(intent, "发送"));
                            }
                        }
                    });
        } else {
            String appDir = "file://" + file.getAbsolutePath();
            Uri uri = Uri.parse(appDir);
            // 发送
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.setType("*/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(Intent.createChooser(intent, "发送"));
        }
    }

    /**
     * 从Asset文件夹中拷贝到sd卡文件夹中
     *
     * @param assetName
     * @param file
     * @return
     */
    public static String copyFile(String assetName, File file) {
        AssetManager assetManager = BaseApp.context.getAssets();
        InputStream inputStream = null;
        FileOutputStream out;
        try {
            inputStream = assetManager.open(assetName);
            out = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            inputStream.close();
            inputStream = null;
            out.flush();
            out.close();
            out = null;
            return file.getAbsolutePath();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 是否是json数据
     *
     * @param value
     * @return
     */
    public static JSONObject isJson(String value) {
        try {
            return new JSONObject(value);
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * 是否是整数
     *
     * @param value
     * @return
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isEmpty(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return true;
        } else {
            if (msg.equalsIgnoreCase("null")) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean EditTextEmpty(EditCheckBean... array) {
        if (array != null) {
            for (EditCheckBean bean : array) {
                if (isEmpty(bean.getValues())) {
                    ToastUtil.showActionResult(bean.getKey() + "不能为空！", false);
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }


}
