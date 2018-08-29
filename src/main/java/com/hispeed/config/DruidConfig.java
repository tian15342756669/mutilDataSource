package com.hispeed.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.*;

import static sun.util.locale.LanguageTag.SEP;


/**
 * Druid的配置类
 */
@Configuration
@EnableTransactionManagement
public class DruidConfig implements EnvironmentAware {

    private static final String DATA_SOURCE_PREfIX_CUSTOM = "spring.custom.datasource.";
    private static final String DATA_SOURCE_CUSTOM_NAME = "name";
    private List<String> customDataSourceNames = new ArrayList<>();

    private Environment environment;
    private ConversionService conversionService;

    /**
     * @param environment the enviroment to set(Environment是用于读取application.properties信息的环境对象)
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "dataSource")
    @Primary
    public AbstractRoutingDataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        LinkedHashMap<Object, Object> targetDatasources = new LinkedHashMap<>();
        this.initCustomDataSources(targetDatasources);
        dynamicDataSource.setDefaultTargetDataSource(targetDatasources.get(customDataSourceNames.get(0)));
        dynamicDataSource.setTargetDataSources(targetDatasources);
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }

    private void initCustomDataSources(LinkedHashMap<Object, Object> targetDataResources)
    {
        RelaxedPropertyResolver property =
                new RelaxedPropertyResolver(environment, DATA_SOURCE_PREfIX_CUSTOM);
        String dataSourceNames = property.getProperty(DATA_SOURCE_CUSTOM_NAME);
        if(StringUtils.isEmpty(dataSourceNames))
        {
            System.out.println("The multiple data source list are empty.");
        }
        else{
            //获取配置文件中配置的druid属性参数
            RelaxedPropertyResolver springDataSourceProperty =
                    new RelaxedPropertyResolver(environment, "spring.datasource.");

            Map<String, Object> druidPropertiesMaps = springDataSourceProperty.getSubProperties("druid.");
            Map<String,Object> druidValuesMaps = new HashMap<>();
            for(String key:druidPropertiesMaps.keySet())
            {
                System.out.println("druid的属性值:" + key);
                String druidKey = "spring.datasource.druid." + key;
                druidValuesMaps.put(druidKey,druidPropertiesMaps.get(key));
            }

            MutablePropertyValues dataSourcePropertyValue = new MutablePropertyValues(druidValuesMaps);
            //循环读取到的多数据源配置
            for (String dataSourceName : dataSourceNames.split(",")) {
                try {
                    Map<String, Object> dsMaps = property.getSubProperties(dataSourceName+".");

                    for(String dsKey : dsMaps.keySet())
                    {
                        if(dsKey.equals("type"))
                        {
                            dataSourcePropertyValue.addPropertyValue("spring.datasource.type", dsMaps.get(dsKey));
                        }
                        else
                        {
                            String druidKey = "spring.datasource.druid." + dsKey;
                            dataSourcePropertyValue.addPropertyValue(druidKey, dsMaps.get(dsKey));
                        }
                    }

                    DataSource ds = dataSourcebuild(dataSourcePropertyValue);
                    if(null != ds){
                        if(ds instanceof DruidDataSource)
                        {
                            DruidDataSource druidDataSource = (DruidDataSource)ds;
                            druidDataSource.setName(dataSourceName);
                            //initDruidFilters(druidDataSource);
                        }

                        customDataSourceNames.add(dataSourceName);
                        DynamicDataSourceContextHolder.datasourceId.add(dataSourceName);
                        targetDataResources.put(dataSourceName,ds);

                    }
                    System.out.println("Data source initialization 【"+dataSourceName+"】 successfully ...");
                } catch (Exception e) {
                    System.out.println("Data source initialization【"+dataSourceName+"】 failed ...exception reson:" + e);
                }
            }
        }
    }

    /**
     * @Title: DataSourcebuild
     * @Description: 创建数据源
     * @param dataSourcePropertyValue 数据源创建所需参数
     *
     * @return DataSource 创建的数据源对象
     */
    public DataSource dataSourcebuild(MutablePropertyValues dataSourcePropertyValue)
    {
        DataSource ds = null;

        if(dataSourcePropertyValue.isEmpty()){
            return ds;
        }

        String type = dataSourcePropertyValue.get("spring.datasource.type").toString();

        if(!StringUtils.isEmpty(type))
        {
            if(StringUtils.equals(type,DruidDataSource.class.getTypeName()))
            {
                ds = new DruidDataSource();

                RelaxedDataBinder dataBinder = new RelaxedDataBinder(ds, "spring.datasource.druid.");
                dataBinder.setConversionService(conversionService);
                dataBinder.setIgnoreInvalidFields(false);
                dataBinder.setIgnoreNestedProperties(false);
                dataBinder.setIgnoreUnknownFields(true);
                dataBinder.bind(dataSourcePropertyValue);
            }
        }
        return ds;
    }
}
