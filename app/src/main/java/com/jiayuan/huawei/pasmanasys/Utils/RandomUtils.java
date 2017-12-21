package com.jiayuan.huawei.pasmanasys.Utils;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class RandomUtils {

    public static int getRandomNum(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }

}
