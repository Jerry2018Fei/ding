package com.saas.utils.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 丁鹏飞
 * Date: 2017/12/11 15:15
 * Title:十六进制转换工具类
 * Describe:
 */
public class HexUtil {
    private static Logger logger = LoggerFactory.getLogger(HexUtil.class);

    /**
     * 将十六进制的转换成可见的字符串
     *
     * @param hexStr String
     * @return String
     */
    public static byte[] decode(String hexStr) {
        byte[] bytes = new byte[hexStr.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (0xff & Integer.parseInt(hexStr.substring(i * 2, i * 2 + 2), 16));
        }

        return bytes;
    }

    /**
     * 转化成十六进制
     *
     * @param buffer byte[]
     * @return String
     */
    public static String encode(byte[] buffer) {
        StringBuilder dump = new StringBuilder();

        try {
            for (byte b : buffer) {
                dump.append(Character.forDigit((b >> 4) & 0x0f, 16)).append(Character.forDigit(b & 0x0f, 16));
            }
        } catch (Throwable t) {
            logger.error(t.getLocalizedMessage());
        }

        return dump.toString();
    }
}



