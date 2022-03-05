package com.jemmy.rocketmq.test.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chengzhujiang
 * @since 2019/8/1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    /**
     * 需要登录
     */
    boolean needLogin() default true;

    /**
     * 需要管理员权限
     */
    boolean needAdmin() default false;
}
