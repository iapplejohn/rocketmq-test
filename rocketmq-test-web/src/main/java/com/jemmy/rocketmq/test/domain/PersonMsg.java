package com.jemmy.rocketmq.test.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhujiang.cheng
 * @since 2020/5/14
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonMsg implements Serializable {

    private static final long serialVersionUID = 7254503116754608721L;

    private String name;

    private Integer age;

    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }
}
