package com.jemmy.rocketmq.test.exception;

/**
 * @author chengzhujiang
 * @since 2019/8/1
 */
public class NotLoginException extends RuntimeException {

    private static final long serialVersionUID = -8446370091922704401L;

    public NotLoginException() {
    }

    public NotLoginException(String message) {
        super(message);
    }
}
