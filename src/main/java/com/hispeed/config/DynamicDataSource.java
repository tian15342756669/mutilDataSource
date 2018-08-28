package com.hispeed.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by dengtg on 2018-8-28.
 */

public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 获得数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDateSoureType();
    }

}