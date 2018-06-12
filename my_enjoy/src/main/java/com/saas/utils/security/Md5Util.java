package com.saas.utils.security;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 丁鹏飞
 * Date: 2017/12/11 15:14
 * Title:MD5工具类
 * Describe:
 */
public class Md5Util {

    /**
     * 进行MD5数字摘要
     * @param buf byte[]  要进行MD5加密的内容
     * @return byte[]
     */
    public static byte[] digest(byte[] buf) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(buf);

            return md.digest();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    /**
     * 取得16进制的MD5值
     * @param buf byte[]  要进行MD5加密的内容
     * @return String
     */
    public static String getHexMD5(byte[] buf) {
        return HexUtil.encode(digest(buf));
    }

    /**
     * 取得16进制的MD5值
     * @param text String
     * @return String
     */
    public static String getHexMD5(String text) {
        return getHexMD5(text.getBytes());
    }

    public static String get32MD5(String str)
    {
        MessageDigest messageDigest = null;
        try
        {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e)
        {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++)
        {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }
}



