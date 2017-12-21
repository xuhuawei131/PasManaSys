package com.jiayuan.huawei.hwframeworklib.utis;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * $desc$
 * http://www.360doc.com/content/12/0314/12/2793098_194239798.shtml
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class RunApkModule {
    public void LoadAPK(Context context, Bundle paramBundle, String dexpath, String dexoutputpath) {
        ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
        DexClassLoader localDexClassLoader = new DexClassLoader(dexpath,
                dexoutputpath, null, localClassLoader);
        try {
            PackageInfo plocalObject = context.getPackageManager()
                    .getPackageArchiveInfo(dexpath, PackageManager.GET_PERMISSIONS
                            | PackageManager.GET_SERVICES
                            | PackageManager.GET_RECEIVERS);

            if ((plocalObject.activities != null)
                    && (plocalObject.activities.length > 0)) {
                String activityname = plocalObject.activities[0].name;
                Log.d("xhw", "activityname = " + activityname);

                Class localClass = localDexClassLoader.loadClass(activityname);
                Constructor localConstructor = localClass
                        .getConstructor(new Class[]{});
                Object instance = localConstructor.newInstance(new Object[]{});
                Log.d("xhw", "instance = " + instance);

                Method localMethodSetActivity = localClass.getDeclaredMethod(
                        "setActivity", new Class[]{Activity.class});
                localMethodSetActivity.setAccessible(true);
                localMethodSetActivity.invoke(instance, new Object[]{this});

                Method methodonCreate = localClass.getDeclaredMethod(
                        "onCreate", new Class[]{Bundle.class});
                methodonCreate.setAccessible(true);
                methodonCreate.invoke(instance, new Object[]{paramBundle});
            }
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
