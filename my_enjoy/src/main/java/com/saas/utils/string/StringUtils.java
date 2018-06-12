package com.saas.utils.string;

import com.saas.system.exception.StringException;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils extends org.springframework.util.StringUtils {
    private static final String CHARSET_NAME = "UTF-8";
    private static final char SEPARATOR = '_';

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 转换为Double类型
     */
    private static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(val.toString().trim());
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Long类型
     */
    private static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val, int defaultValue) {
        if (val == null || StringUtils.isEmpty(val.toString())) {
            return defaultValue;
        }
        return toLong(val).intValue();
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }


    /**
     * 驼峰命名法工具
     * @return
     * 		toCamelCase("hello_world") == "helloWorld"
     * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * 		toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     * @return
     * 		toCamelCase("hello_world") == "helloWorld"
     * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * 		toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     * @return
     * 		toCamelCase("hello_world") == "helloWorld"
     * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * 		toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean nextUpperCase = true;
            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }
            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }


    /**
     * author  魏鹏
     * date    2017年11月7日
     * title   去掉指定字符串的开头的指定字符
     * @param stream  原始字符串
     * @param trim  需要去掉的字符串
     * @return
     */
    public static String trimStart(String stream, String trim) {
        // null或者空字符串的时候不处理
        if (stream == null || stream.length() == 0 || trim == null || trim.length() == 0) {
            return stream;
        }
        // 要删除的字符串结束位置
        int end;
        // 正规表达式
        String regPattern = "[" + trim + "]*+";
        Pattern pattern = Pattern.compile(regPattern, Pattern.CASE_INSENSITIVE);
        // 去掉原始字符串开头位置的指定字符
        Matcher matcher = pattern.matcher(stream);
        if (matcher.lookingAt()) {
            end = matcher.end();
            stream = stream.substring(end);
        }
        // 返回处理后的字符串
        return stream;
    }

    public static String convertStringListToString(List<String> list) throws StringException {
        if(CollectionUtils.isEmpty(list)){
            throw new StringException("集合不可以为空");
        }
        String result="";
        for(String s:list){
            if(StringUtils.isEmpty(result)){
                result="\'"+s+"\'";
            }else {
                result +=","+"\'"+s+"\'";
            }
        }
        return result;
    }
}
