package com.universe.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @author 刘亚楼
 * @date 2020/1/14
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.universe.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig {

	@ConfigurationProperties(prefix = "spring.datasource.druid")
	@Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource druidDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(DruidDataSource druidDataSource) throws Exception {
		MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
		factoryBean.setDataSource(druidDataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		factoryBean.setConfigLocation(resolver.getResource("classpath:mybatis/mybatis-config.xml"));
		factoryBean.setMapperLocations(resolver.getResources("classpath*:mybatis/mapper/*Mapper.xml"));
		return factoryBean.getObject();
	}

	@Bean
	public DataSourceTransactionManager platformTransactionManager(DruidDataSource druidDataSource) {
		return new DataSourceTransactionManager(druidDataSource);
	}
}
