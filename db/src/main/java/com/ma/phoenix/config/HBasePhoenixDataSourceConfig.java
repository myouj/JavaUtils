package com.ma.phoenix.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@PropertySource(value = "classpath:application.yml")
@MapperScan(basePackages = {
        "com.ma.phoenix.test.dao"
    },
    sqlSessionFactoryRef = HBasePhoenixDataSourceConfig.HBASEPHOENIX_SQL_SESSION_FACTORY
)
public class HBasePhoenixDataSourceConfig {

    static final String HBASEPHOENIX_SQL_SESSION_FACTORY = "hbasePhoenixSqlSessionFactory";
    static final String MAPPER_LOCATION = "classpath:com/ma/phoenix/test/dao/*.xml";


    @Value("${spring.datasource.phoenix.jdbc-url}")
    private String url;

    @Value(value = "${spring.datasource.phoenix.driver-class-name}")
    private String driverClass;


    /**
     * 1.获取数据源
     * @return
     */
    @Bean(name = "hBasePhoenixDataSource")
    public DataSource hBasePhoenixDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        //dataSource.setConnectProperties(properties);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }

    /**
     * 2.创建事务管理
     * @param dataSource
     * @return
     */
    @Bean(name = "hbasePhoenixTransactionManager")
    public DataSourceTransactionManager hbasePhoenixTransactionManager(
            @Qualifier("hBasePhoenixDataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建 session工厂
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = HBASEPHOENIX_SQL_SESSION_FACTORY)
    public SqlSessionFactory hbasePhoenixSqlSessionFactory(
            @Qualifier("hBasePhoenixDataSource")DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(HBasePhoenixDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    public static void main(String[] args) throws IOException {
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources(HBasePhoenixDataSourceConfig.MAPPER_LOCATION);
        System.out.println(resources.length);
    }


}
