package com.saas.system.exception;

/**
 * @Auth: dingpengfei
 * @Date: 2017/9/7 11:55
 * @Title: web层异常类
 * @Describe: web层接受到的所有异常与信息应该使用ServerWebAppException捕获
 **/
public class ServerWebAppException extends Exception {
    private String message; //异常信息

    public ServerWebAppException(String message) {
        this.message = message;
    }
}
