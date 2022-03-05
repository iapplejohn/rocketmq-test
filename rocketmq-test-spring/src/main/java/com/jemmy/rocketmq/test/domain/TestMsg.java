package com.jemmy.rocketmq.test.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhujiang.cheng
 * @since 2020/7/15
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestMsg implements Serializable {

    private static final long serialVersionUID = 2560586793512169569L;

    private String name;

    @Override
    public String toString() {
        return "TestMsg{" +
            "name='" + name + '\'' +
            '}';
    }
}
