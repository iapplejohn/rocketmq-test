package com.jemmy.rocketmq.test.domain;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author chengzhujiang
 * @since 2019/8/3
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1598654616090519303L;

    private String username;

    private String password;

    private String avatar;

    private List<String> roles;
}
