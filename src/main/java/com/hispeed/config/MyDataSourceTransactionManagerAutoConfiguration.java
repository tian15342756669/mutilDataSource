package com.hispeed.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

/**
 * Created by dengtg on 2017/3/20.
 */
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter({MybatisConfiguration.class})
public class MyDataSourceTransactionManagerAutoConfiguration
        extends DataSourceTransactionManagerAutoConfiguration {

    @Resource(name = "roundRobinDataSourceProxy")
    private AbstractRoutingDataSource abstractRoutingDataSource;

    /**
     * 自定义事务
     * MyBatis自动参与到spring事务管理中，无需额外配置，
     * 只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。
     * @return
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManagers() {
        System.out.println("-------------------- transactionManager init ---------------------");
        return new DataSourceTransactionManager(abstractRoutingDataSource);
    }
}
