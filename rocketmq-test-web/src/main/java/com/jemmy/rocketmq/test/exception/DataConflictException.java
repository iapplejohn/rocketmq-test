package com.jemmy.rocketmq.test.exception;

/**
 * @author chengzhujiang
 * @since 2019/8/4
 */
public class DataConflictException extends RuntimeException {

    public DataConflictException(String message) {
        super(message);
    }
}
