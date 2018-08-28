package com.hispeed.config;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengtg on 2017/3/20.
 */
@Configuration
@AutoConfigureAfter({DataSourceConfig.class})
public class MybatisConfiguration extends MybatisAutoConfiguration {

    @Resource(name = "dataSource0")
    private DataSource dataSource0;

    @Resource(name = "dataSource1")
    private DataSource dataSource1;

    @Resource(name = "dataSource2")
    private DataSource dataSource2;

    @Value("${datasource.readSize}")
    private String dataSourceSize;

    public MybatisConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider
            , ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider) {
       super(properties, interceptorsProvider, resourceLoader, databaseIdProvider);
    }

    /**
     * 有多少个数据源就要配置多少个bean
     * @return
     */
    @Bean(name = "roundRobinDataSourceProxy")
    public AbstractRoutingDataSource roundRobinDataSourceProxy() {

        DynamicDataSource proxy = new DynamicDataSource();

        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        int size = Integer.parseInt(dataSourceSize);
//        for (int i = 0; i < size; i++) {
//            targetDataSources.put("dataSource"+i, SpringContextHolder.getBean("readDataSource" + (i + 1)));
//        }

        targetDataSources.put("dataSource0", dataSource0);
        DynamicDataSourceContextHolder.datasourceId.add("dataSource0");

        targetDataSources.put("dataSource1", dataSource1);
        DynamicDataSourceContextHolder.datasourceId.add("dataSource1");

        targetDataSources.put("dataSource2", dataSource2);
        DynamicDataSourceContextHolder.datasourceId.add("dataSource2");

        proxy.setDefaultTargetDataSource(dataSource0);
        proxy.setTargetDataSources(targetDataSources);

        return proxy;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactoryReload() throws Exception {
        System.out.println("-------------------- 重载父类 sqlSessionFactory init ---------------------");
        return super.sqlSessionFactory(roundRobinDataSourceProxy());
    }


//    private void initCustomDataSources(LinkedHashMap<Object, Object> targetDataResources) {
//        RelaxedPropertyResolver property =
//                new RelaxedPropertyResolver(environment, DATA_SOURCE_PREfIX_CUSTOM);
//        String dataSourceNames = property.getProperty(DATA_SOURCE_CUSTOM_NAME);
//        if(StringUtils.isEmpty(dataSourceNames))
//        {
//            System.out.println("The multiple data source list are empty.");
//        }
//        else{
//            RelaxedPropertyResolver springDataSourceProperty =
//                    new RelaxedPropertyResolver(environment, "spring.datasource.");
//
//            Map<String, Object> druidPropertiesMaps = springDataSourceProperty.getSubProperties("druid.");
//            Map<String,Object> druidValuesMaps = new HashMap<>();
//            for(String key:druidPropertiesMaps.keySet())
//            {
//                String druidKey = AppConstants.DRUID_SOURCE_PREFIX + key;
//                druidValuesMaps.put(druidKey,druidPropertiesMaps.get(key));
//            }
//
//            MutablePropertyValues dataSourcePropertyValue = new MutablePropertyValues(druidValuesMaps);
//
//            for (String dataSourceName : dataSourceNames.split(SEP)) {
//                try {
//                    Map<String, Object> dsMaps = property.getSubProperties(dataSourceName+".");
//
//                    for(String dsKey : dsMaps.keySet())
//                    {
//                        if(dsKey.equals("type"))
//                        {
//                            dataSourcePropertyValue.addPropertyValue("spring.datasource.type", dsMaps.get(dsKey));
//                        }
//                        else
//                        {
//                            String druidKey = DRUID_SOURCE_PREFIX + dsKey;
//                            dataSourcePropertyValue.addPropertyValue(druidKey, dsMaps.get(dsKey));
//                        }
//                    }
//
//                    DataSource ds = dataSourcebuild(dataSourcePropertyValue);
//                    if(null != ds){
//                        if(ds instanceof DruidDataSource)
//                        {
//                            DruidDataSource druidDataSource = (DruidDataSource)ds;
//                            druidDataSource.setName(dataSourceName);
//                            initDruidFilters(druidDataSource);
//                        }
//
//                        customDataSourceNames.add(dataSourceName);
//                        DynamicDataSourceContextHolder.datasourceId.add(dataSourceName);
//                        targetDataResources.put(dataSourceName,ds);
//
//                    }
//                    logger.info("Data source initialization 【"+dataSourceName+"】 successfully ...");
//                } catch (Exception e) {
//                    logger.error("Data source initialization【"+dataSourceName+"】 failed ...", e);
//                }
//            }
//        }
//    }
}
