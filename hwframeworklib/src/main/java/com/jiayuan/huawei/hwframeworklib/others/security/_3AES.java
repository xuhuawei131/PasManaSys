/**
		* DataUtil.java V1.0 2014年7月17日 下午9:11:08
		*
		* Copyright JIAYUAN Co. ,Ltd. All rights reserved.
		*
		* Modification history(By WAH-WAY):
		*
		* Description:
		*/

package com.jiayuan.huawei.hwframeworklib.others.security;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
        * 功能描述：
        * 主要实现对数据进行加密、解密、排序等
        * @author 许华维(WAH-WAY)
        *
        * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class _3AES {
/**
 * 所有大写拉丁字母字符
 */
public static final String UPPER_LETTER_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
/**
 * 所有小写拉丁字母字符
 */
public static final String LOWER_LETTER_CHAR = "abcdefghijklmnopqrstuvwxyz";
/**
 * 所有数字字符
 */
public static final String ARAB_NUMBER_CHAR = "0123456789";
/**
 * 所有大写中文数字字符
 */
public static final String UPPER_CHINA_NUMBER_CHAR = "零壹贰叁肆伍陆柒捌玖拾佰仟萬億";
/**
 * 所有小写中文数字字符
 */
public static final String LOWER_CHINA_NUMBER_CHAR = "零一二三四五六七八九十百千万亿";

private _3AES() {
}

/**
 * 随机生成唯一UUID字符串
 *
 * @return 返回随机产生的唯一字符
 */
public static String getOnlyStr() {
    return UUID.randomUUID().toString();
}

/**
 * 随机生成字符串
 *
 * @param length
 *            指定生产字符串长度
 * @param chars
 *            生成的字符串中的每个字符都是随机从字符粗chars取出字符
 * @return 返回随机生成的字符 (不能保证不重复)
 */
public static String generateStr(int length, String chars) {
    String result = null;
    StringBuffer sb = new StringBuffer();
    Random random = new Random();
    int size = chars.length();
    for (int i = 0; i < length; i++) {
        sb.append(chars.charAt(random.nextInt(size)));
    }
    result = sb.toString();
    return result;
}

/**
 * 十六进制数值字符串转byte类数据
 *
 * @param strhex
 *            数据源
 * @return 返回转换后的byte数据
 */
public static byte[] hex2byte(String strhex) {
    if (strhex == null) {
        return null;
    }
    int l = strhex.length();
    if (l % 2 == 1) {
        return null;
    }
    byte[] b = new byte[l / 2];
    for (int i = 0; i != l / 2; i++) {
        b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                16);
    }
    return b;
}

/**
 * byte数据转十六进制数值字符串
 *
 * @param b
 *            byte数据源
 * @return 返回转换字符串数据
 */
public static String byte2hex(byte[] b) {
    StringBuffer hs = new StringBuffer(b.length);
    String stmp = "";
    int len = b.length;
    for (int n = 0; n < len; n++) {
        stmp = Integer.toHexString(b[n] & 0xFF);
        if (stmp.length() == 1)
            hs = hs.append("0").append(stmp);
        else {
            hs = hs.append(stmp);
        }
    }
    return String.valueOf(hs);
}

/**
 * MD5加密(一般认为无法解密，不过现在已经被证明可以解密)
 *
 * 信息-摘要
 *
 * @param data
 *            数据源
 * @return 返回加密的字符串数据
 */
public static String encryptData2MD5(String data) {
    String result = null;
    try {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data.getBytes("UTF-8"));
        result = byte2hex(md5.digest());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

/**
 * SHA加密 (不可以解密)
 *
 * @param data
 *            数据源
 * @return 返回加密的字符串数据
 */
public static String encyptData2SHA(String data) {
    String result = null;
    try {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(data.getBytes("UTF-8"));
        result = byte2hex(sha.digest());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

/**
 * 简单可逆加密解密
 *
 * @param data
 *            数据源
 * @return 解密或加密后的数据
 */
public static String encryptDataReversible(String data) {
    char[] chars = data.toCharArray();
    for (int i = 0; i < chars.length; i++) {
        chars[i] = (char) (chars[i] ^ '1');
    }
    return new String(chars);
}

/**
 * 获取用于加密的公钥和私钥的钥匙包
 *
 * @param type
 *            加密方式
 * @param size
 *            生成公钥和私钥的字符大小
 * @return 换回生成的钥匙包
 */
public static KeyPair getKeyCase(String type, int size) {
    KeyPair keyPair = null;
    try {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(type);
        keyPairGen.initialize(size);
        keyPair = keyPairGen.generateKeyPair();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return keyPair;
}

/**
 * 获取消息验证码
 *
 * @param data
 *            数据源
 * @param key
 *            获取数据源钥匙
 * @return 返回加密的消息验证码
 */
public static String getEncryptHMACMD5(String data, String key) {
    String result = null;
    try {
        byte[] keyByte = key.getBytes("UTF-8");
        SecretKeySpec sks = new SecretKeySpec(keyByte, "HMACMD5");
        Mac mac = Mac.getInstance("HMACMD5");
        mac.init(sks);
        mac.update(data.getBytes());
        byte[] certifyCode = mac.doFinal();
        if (certifyCode != null) {
            result = byte2hex(certifyCode);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

/**
 * 加密
 *
 * @param src
 *            数据源
 * @param key
 *            密钥
 * @param type
 *            加密类型 RAS DSA
 * @return 返回加密后的数据
 * @throws Exception
 */
public static byte[] encrypt(byte[] src, byte[] key, String type) {
    byte[] reuslt = null;
    try {
        SecretKeySpec sKeySpec = new SecretKeySpec(key, type);
        Cipher cipher = Cipher.getInstance(type);
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
        reuslt = cipher.doFinal(src);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return reuslt;
}

/**
 * 解密
 *
 * @param src
 *            数据源
 * @param key
 *            密匙
 * @param type
 *            加密类型 RSA DSA
 * @return 返回解密后的原始数据
 * @throws Exception
 */
public static byte[] decrypt(byte[] src, byte[] key, String type) {
    byte[] reuslt = null;
    try {
        SecretKeySpec sKeySpec = new SecretKeySpec(key, type);
        Cipher cipher = Cipher.getInstance(type);
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
        reuslt = cipher.doFinal(src);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return reuslt;
}

/**
 * 使用公钥加密
 *
 * @param publicKey
 *            加密公钥
 * @param srcBytes
 *            数据源
 * @param type
 *            加密类型主要有 RSA
 * @return 返回加密后的数据
 */
public static byte[] encrypt(PublicKey publicKey, byte[] srcBytes,
							 String type) {
    byte[] result = null;
    if (publicKey != null) {
        try {
            Cipher cipher = Cipher.getInstance(type);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result = cipher.doFinal(srcBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return result;
}

/**
 * 使用私钥加密
 *
 * @param privateKey
 *            加密私钥
 * @param srcBytes
 *            数据源
 * @param type
 *            加密类型 RSA DSA
 * @return 返回加密后的数据
 */
public static byte[] encrypt(PrivateKey privateKey, byte[] srcBytes,
							 String type) {
    byte[] result = null;
    if (privateKey != null) {
        try {
            Cipher cipher = Cipher.getInstance(type);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            result = cipher.doFinal(srcBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return result;
}

/**
 * 使用私钥解密
 *
 * @param privateKey
 *            解密私钥
 * @param serBytes
 *            数据源
 * @param type
 *            解密类型 RSA
 * @return 解密后的数据
 */
public static byte[] decrypt(PrivateKey privateKey, byte[] serBytes,
							 String type) {
    byte[] result = null;
    if (privateKey != null) {
        try {
            Cipher cipher = Cipher.getInstance(type);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(serBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return result;
}

/**
 * 使用公钥解密
 *
 * @param privateKey
 *            解密私钥
 * @param serBytes
 *            数据源
 * @param type
 *            解密类型 RSA DSA
 * @return 解密后的数据
 */
public static byte[] decrypt(PublicKey publicKey, byte[] serBytes,
							 String type) {
    byte[] result = null;
    if (publicKey != null) {
        try {
            Cipher cipher = Cipher.getInstance(type);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(serBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    return result;
}

/**
 * AES加密
 *
 * @param data
 *            数据源
 * @param key
 *            加密钥匙
 * @return 加密后的数据
 */
public static String encryptAESStr(String data, String key) {
    String result = null;
    if (key != null && key.length() == 16) {
        try {
            byte[] resultbytes = encrypt(data.getBytes(),
                    key.getBytes("UTF-8"), "AES");
            if (resultbytes != null) {
                result = byte2hex(resultbytes);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    return result;
}

/**
 * AES 解密
 *
 * @param data
 *            数据源
 * @param key
 *            解密钥匙
 * @return 解密后的数据
 */
public static String decryptAESStr(String data, String key) {
    String result = null;
    if (key != null && key.length() == 16) {
        try {
            byte[] databytes = hex2byte(data);
            byte[] resultbytes = decrypt(databytes, key.getBytes("UTF-8"),
                    "AES");
            if (resultbytes != null) {
                result = new String(resultbytes);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    return result;
}

/**
 * 3DES加密
 *
 * @param data
 *            数据源
 * @param key
 *            加密钥匙
 * @return 返回加密后的数据
 */
public static String decrypt3DESStr(String data, String key) {
    String result = null;
    if (key != null && key.length() == 16) {
        try {
            byte[] databytes = hex2byte(data);
            byte[] resultbytes = decrypt(databytes, key.getBytes("UTF-8"),
                    "DESede");
            if (resultbytes != null) {
                result = new String(resultbytes);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    return result;
}

/**
 * 3DES解密
 *
 * @param data
 *            数据源
 * @param key
 *            解密钥匙
 * @return 返回解密后的原始数据
 */
public static String encrypt3DESStr(String data, String key) {
    String result = null;
    if (key != null && key.length() == 16) {
        try {
            byte[] resultbytes = encrypt(data.getBytes(),
                    key.getBytes("UTF-8"), "DESede");
            if (resultbytes != null) {
                result = byte2hex(resultbytes);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    return result;
}

/**
 * 3DES解密
 *
 * @param data
 *            数据源
 * @param key
 *            解密钥匙
 * @return 返回解密后的原始数据
 */
public static String decryptDESStr(String data, String key) {
    String result = null;
    if (key != null && key.length() == 8) {
        try {
            byte[] databytes = hex2byte(data);
            byte[] resultbytes = decrypt(databytes, key.getBytes("UTF-8"),
                    "DES");
            if (resultbytes != null) {
                result = new String(resultbytes);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    return result;
}

/**
 * DES加密
 *
 * @param data
 *            数据源
 * @param key
 *            加密钥匙
 * @return 返回加密后的数据
 */
public static String encryptDESStr(String data, String key) {
    String result = null;
    if (key != null && key.length() == 8) {
        try {
            byte[] resultbytes = encrypt(data.getBytes(),
                    key.getBytes("UTF-8"), "DES");
            if (resultbytes != null) {
                result = byte2hex(resultbytes);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    return result;
}

/**
 * RSA私密加密
 *
 * @param data
 *            数据源
 * @param key
 *            加密钥匙
 * @return 返回加密的数据
 */
public static String encryptPrivateKeyStr(String data,
										  PrivateKey privateKey, String type) {
    String result = null;
    try {
        byte[] resultbytes = encrypt(privateKey, data.getBytes(), type);
        if (resultbytes != null) {
            result = byte2hex(resultbytes);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

/**
 * RSA公钥加密
 *
 * @param data
 *            数据源
 * @param key
 *            加密钥匙
 * @return 返回加密后的数据
 */
public static String encryptPublicKeyStr(String data, PublicKey publicKey,
										 String type) {
    String result = null;
    try {
        byte[] resultbytes = encrypt(publicKey, data.getBytes(), type);
        if (resultbytes != null) {
            result = byte2hex(resultbytes);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

/**
 * RSA私密解密
 *
 * @param data
 *            数据源
 * @param key
 *            解密钥匙
 * @return 返回解密后的数据
 */
public static String decryptPrivateKeyStr(String data,
										  PrivateKey privateKey, String type) {
    String result = null;
    try {
        byte[] databytes = hex2byte(data);
        byte[] resultbytes = decrypt(privateKey, databytes, type);
        if (resultbytes != null) {
            result = new String(resultbytes);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

/**
 * RSA公钥解密
 *
 * @param data
 *            数据源
 * @param key
 *            解密钥匙
 * @return 返回解密后的数据
 */
public static String decryptPublicKeyStr(String data, PublicKey publicKey,
										 String type) {
    String result = null;
    try {
        byte[] databytes = hex2byte(data);
        byte[] resultbytes = decrypt(publicKey, databytes, type);
        if (resultbytes != null) {
            result = new String(resultbytes);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

/**
 * DSA私钥数据签名
 *
 * @param data
 *            数据源
 * @param privateKey
 *            签名私钥
 * @return 返回已经签名了的数据
 */
public static String signetPrivateKeyDSAStr(String data,
											PrivateKey privateKey) {
    String result = null;
    try {
        java.security.Signature signet = java.security.Signature
                .getInstance("DSA");
        signet.initSign(privateKey);
        signet.update(data.getBytes());
        byte[] resultbytes = signet.sign();
        if (resultbytes != null) {
            result = byte2hex(resultbytes);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

/**
 * DSA公钥验证
 *
 * @param data
 *            原始数据源
 * @param publicKey
 *            验证签名的公钥
 * @param signed
 *            签名的数据源
 * @return 返回验证是否通过
 */
public static boolean verificationPublicKeyDSA(String data,
											   PublicKey publicKey, String signed) {
    boolean result = false;
    try {
        java.security.Signature signetcheck = java.security.Signature
                .getInstance("DSA");
        signetcheck.initVerify(publicKey);
        signetcheck.update(data.getBytes());
        if (signetcheck.verify(hex2byte(signed))) {
            result = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}
}