package com.saas;

import java.lang.annotation.*;

/**
 * 自定义注解 保密机制 有此注解的请求要进行用户校验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessSecurity {
    public String role() default "admin";//预留字段
}
