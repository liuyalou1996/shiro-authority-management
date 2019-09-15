package com.universe.config.shiro;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;

import com.universe.common.entity.properties.ShiroProperties;
import com.universe.config.shiro.realm.ShiroJdbcRealm;
import com.universe.config.shiro.serializer.ProtostuffSerializer;

import redis.clients.jedis.JedisPool;

@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
@PropertySource("classpath:shiro/shiro.properties")
public class ShiroConfig {

  static class ShiroCoreComponentConfig {

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
    public RedisManager redisManager(JedisPool jedisPool) {
      RedisManager redisManager = new RedisManager();
      redisManager.setJedisPool(jedisPool);
      return redisManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager) {
      RedisCacheManager cacheManager = new RedisCacheManager();
      cacheManager.setRedisManager(redisManager);

      ProtostuffSerializer serializer = new ProtostuffSerializer();
      cacheManager.setKeySerializer(serializer);
      cacheManager.setValueSerializer(serializer);

      return cacheManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
      RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
      redisSessionDAO.setRedisManager(redisManager);
      return redisSessionDAO;
    }

    @Bean
    public DefaultWebSessionManager defaultWebSessoinManager(RedisSessionDAO redisSessionDAO) {
      DefaultWebSessionManager sessionManger = new DefaultWebSessionManager();
      sessionManger.setSessionDAO(redisSessionDAO);
      return sessionManger;
    }

    @Bean
    public SecurityManager securityManager(ShiroJdbcRealm shiroJdbcRealm, RedisCacheManager redisCacheManager,
        DefaultWebSessionManager defaultWebSessoinManager) {
      DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
      securityManager.setRealm(shiroJdbcRealm);
      securityManager.setCacheManager(redisCacheManager);
      securityManager.setSessionManager(defaultWebSessoinManager);
      return securityManager;
    }
  }

  static class ShiroBeanConfig {

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
      return new LifecycleBeanPostProcessor();
    }

  }

  static class ShiroAnnotationConfig {

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
      DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
      // 设为true即可使用Jdk动态代理，也可使用Cglib代理
      creator.setProxyTargetClass(true);
      return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
      AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
      advisor.setSecurityManager(securityManager);
      return advisor;
    }

  }

}
