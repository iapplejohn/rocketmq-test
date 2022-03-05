package com.jemmy.rocketmq.test.exception;

/**
 * @author chengzhujiang
 * @since 2019/8/4
 */
public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2251823256890358243L;

    public DataNotFoundException() {

    }

    public DataNotFoundException(String message, Integer id) {
        super(message);
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
