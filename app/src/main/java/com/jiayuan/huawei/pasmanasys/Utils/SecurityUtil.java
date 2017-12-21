package com.jiayuan.huawei.pasmanasys.Utils;

import com.jiayuan.huawei.hwframeworklib.others.security.Base64Util;
import com.jiayuan.huawei.hwframeworklib.others.security._3AES;
import com.jiayuan.huawei.hwframeworklib.utis.HWUtils;
import com.jiayuan.huawei.pasmanasys.constants.MyConstatns;

/**
 * $desc$
 *
 * @author xuhuawei
 * @time $date$ $time$
 */
public class SecurityUtil {
    //-------------------------aes加密解密-------------------------------------
    public static String encodeAES(String data) {
        if (!HWUtils.isEmpty(data)) {
            String code1 = _3AES.encryptAESStr(data, MyConstatns.CODE_AES);
            return Base64Util.encode(code1);
        } else {
            return null;
        }
    }

    public static String decodeAES(String data) {
        if (!HWUtils.isEmpty(data)) {
            String code1 = Base64Util.decode(data);
            return _3AES.decryptAESStr(code1, MyConstatns.CODE_AES);
        } else {
            return null;
        }
    }

    //-------------------------des加密解密-------------------------------------
    public static String encodeDES(String data) {
        if (!HWUtils.isEmpty(data)) {
            String code1 = _3AES.encryptDESStr(data, MyConstatns.CODE_DES);
            return Base64Util.encode(code1);
        } else {
            return null;
        }
    }

    public static String decodeDES(String data) {
        if (!HWUtils.isEmpty(data)) {
            String code1 = Base64Util.encode(data);
            return _3AES.decryptDESStr(code1, MyConstatns.CODE_DES);
        } else {
            return null;
        }
    }
}
