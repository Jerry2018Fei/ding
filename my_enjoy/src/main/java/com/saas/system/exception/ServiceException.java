package com.saas.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : 丁鹏飞
 * Date : 2018/2/7 16:53
 **/
@Data@NoArgsConstructor@AllArgsConstructor
public class ServiceException extends Exception {
    private String message;

}
