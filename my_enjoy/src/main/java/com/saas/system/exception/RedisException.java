package com.saas.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class RedisException extends Exception {

    private String message;

    public RedisException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
}
