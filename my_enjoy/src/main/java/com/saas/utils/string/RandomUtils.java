package com.saas.utils.string;

import com.saas.system.exception.StringException;

import java.util.Random;

/**
 * 随机值工具类
 * @author Jerry
 * Date 2017/12/15
 */
public class RandomUtils {




    public static String randomDouble(Integer min, Integer max) {
        Random random = new Random();

        return (min + random.nextInt(max - min)) + "." + random.nextInt(Integer.MAX_VALUE);
    }

    /**
     * 随机邮箱地址
     *
     * @param length 长度
     * @return email
     * @throws StringException
     * 字符串异常
     */
    public static String randomEmail(Integer length) throws StringException {
        Integer min = 10;

        if (length < min) {
            throw new StringException("邮箱长度太短，请设置10以上");
        }

        return "";
    }



    /**
     * 随机手机号
     *
     * @return 手机号
     */
    public static String randomPhone() {
        String[]      prefixArray = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        String[]      suffixArray = { "131", "152", "176", "151" };
        Random        random      = new Random();
        StringBuilder phone       = new StringBuilder();

        phone.append(suffixArray[random.nextInt(4)]);

        int length = 8;

        for (int i = 0; i < length; i++) {
            phone.append(prefixArray[random.nextInt(10)]);
        }

        return phone.toString();
    }


}



