package com.universe.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;

import com.universe.common.entity.properties.ShiroProperties;
import com.universe.realm.ShiroJdbcRealm;

@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
@PropertySource("classpath:shiro/shiro.properties")
public class ShiroConfig {

  static class ShiroCoreConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
        ShiroProperties shiroProperties) {
      ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
      shiroFilterFactoryBean.setSecurityManager(securityManager);
      // 设置登录url，默认为login.jsp
      shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
      // 设置无权限跳转的url
      shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());
      // 键为url表达式，值为逗号分隔的过滤器名
      Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
      List<String> anonUrlList = shiroProperties.getAnonUrlList();
      anonUrlList.forEach(anonUrl -> {
        filterChainDefinitionMap.put(anonUrl, DefaultFilter.anon.name());
      });
      filterChainDefinitionMap.put(shiroProperties.getLogoutUrl(), DefaultFilter.logout.name());
      filterChainDefinitionMap.put(shiroProperties.getAuthUrl(), DefaultFilter.authc.name());

      shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
      return shiroFilterFactoryBean;
    }

    @Bean
    public ShiroJdbcRealm shiroJdbcRealm() {
      return new ShiroJdbcRealm();
    }

    @Bean
    public SecurityManager securityManager(ShiroJdbcRealm shiroJdbcRealm) {
      DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
      securityManager.setRealm(shiroJdbcRealm);
      return securityManager;
    }
  }

  static class ShiroBeanConfig {

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
      return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
      return new DefaultAdvisorAutoProxyCreator();
    }
  }

  static class ShiroAnnotationConfig {

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
      AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
      advisor.setSecurityManager(securityManager);
      return advisor;
    }
  }

}
