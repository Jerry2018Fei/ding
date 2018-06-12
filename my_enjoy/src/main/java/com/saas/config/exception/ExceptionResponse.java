package com.saas.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 丁鹏飞
 * Date : 2018/1/16 10:24
 **/
@Data@NoArgsConstructor@AllArgsConstructor
public class ExceptionResponse {
    private Boolean success;
    private String message;
    private Object data;
}
