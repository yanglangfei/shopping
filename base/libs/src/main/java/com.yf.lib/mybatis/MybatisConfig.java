package com.yf.lib.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Slf4j
@Configuration
@ConditionalOnClass({EnableTransactionManagement.class})
//@Import({DataSourceConfig.class})
@MapperScan(basePackages = {MybatisConfig.PACKAGE})
public class MybatisConfig {

    static final String PACKAGE = "com.yf.mapper";
    static final String DOMAIN = "com.yf.model";
    static final String MAPPER_LOCATION = "classpath:mapper/**/*.xml";

    @Value("${define.datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    @Resource(name = "writeDataSource")
    private DataSource writeDataSource;
    @Resource(name = "readDataSources")
    private List<DataSource> readDataSources;

    @Bean
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        log.debug("-----------------------sqlSessionFactory init.-----------------------");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());
        sqlSessionFactoryBean.setTypeAliasesPackage(DOMAIN);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
        DynamicDataSource proxy = new DynamicDataSource(readDataSources.size());
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.write.getType(), writeDataSource);
        //多个读数据库
        for (int i = 0; i < readDataSources.size(); i++) {
            targetDataSources.put(i, readDataSources.get(i));
        }
        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

}
