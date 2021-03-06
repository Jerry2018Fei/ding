package com.saas.utils.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * @author 丁鹏飞
 * Date: 2017/12/11 13:50
 * Title: des加密工具类
 * Describe:
 */
public class DesUtil {
    private final static String DES = "DES";

    /**
     * Description 根据键值进行解密
     * @param data 数据
     * @param key  加密键byte数组
     * @return 解密后的信息
     * @throws Exception 异常
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {

        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey        securekey  = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行解密
     * @param data 数据
     * @param key  加密键byte数组
     * @return 解密后的信息
     * @throws IOException IO异常
     * @throws Exception 异常
     */
    public static String decrypt(String data, String key) throws Exception {
        if (data == null) {
            return null;
        }

        Base64 decoder = new Base64();
        byte[] buf     = decoder.decode(data);
        byte[] bt      = decrypt(buf, key.getBytes());

        return new String(bt);
    }

    /**
     * Description 根据键值进行加密
     * @param data 数据
     * @param key  加密键byte数组
     * @return 加密信息
     * @throws Exception 异常
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {

        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey        securekey  = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行加密
     * @param data 数据
     * @param key  加密键byte数组
     * @return 加密信息
     * @throws Exception 异常
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());

        return new String(new Base64().encode(bt));
    }

//    public static void main(String[] args) {
//
//        try {
//            System.out.println(decrypt("ZVc3Of2qUVKzEy/J664tGg==", "8vKVV@ye8"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}



