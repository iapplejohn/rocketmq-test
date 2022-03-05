package com.jemmy.rocketmq.test.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author chengzhujiang
 * @since 2019/7/20
 */
@Configuration
@MapperScan("com.jemmy.rocketmq.test.dal.mapper")
public class MybatisConfig {

    @Resource
    private DataSource dataSource;

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
