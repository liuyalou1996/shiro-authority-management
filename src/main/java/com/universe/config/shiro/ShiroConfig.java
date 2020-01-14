package com.universe.config.shiro;

import com.universe.common.util.PasswordGenerationUtils;
import com.universe.config.shiro.realm.ShiroJdbcRealm;
import com.universe.pojo.properties.ShiroProperties;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘亚楼
 * @date 2020/1/14
 */
@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
@PropertySource("classpath:shiro/shiro.properties")
public class ShiroConfig {

	static class ShiroCoreComponentConfig {

		@Autowired
		public ShiroProperties shiroProperties;

		@Bean
		public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
			ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
			shiroFilterFactoryBean.setSecurityManager(securityManager);
			// 设置登录url，默认为login.jsp
			shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
			// 设置无权限跳转的url
			shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());
			// 键为url表达式，值为逗号分隔的过滤器名，各过滤器保证顺序
			Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
			List<String> anonUrlList = shiroProperties.getAnonUrlList();
			anonUrlList.forEach(anonUrl -> {
				filterChainDefinitionMap.put(anonUrl, DefaultFilter.anon.name());
			});
			filterChainDefinitionMap.put(shiroProperties.getLogoutUrl(), DefaultFilter.logout.name());
			filterChainDefinitionMap.put(shiroProperties.getAuthUrl(), DefaultFilter.user.name());

			shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
			return shiroFilterFactoryBean;
		}

		@Bean
		public ShiroJdbcRealm shiroJdbcRealm() {
			ShiroJdbcRealm shiroJdbcRealm = new ShiroJdbcRealm();
			HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
			matcher.setHashAlgorithmName(PasswordGenerationUtils.HASH_ALGORITHM);
			matcher.setHashIterations(PasswordGenerationUtils.HASH_ITERATIONS);
			matcher.setStoredCredentialsHexEncoded(true);
			shiroJdbcRealm.setCredentialsMatcher(matcher);
			return shiroJdbcRealm;
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
			sessionManger.setSessionIdUrlRewritingEnabled(false);
			sessionManger.setSessionDAO(redisSessionDAO);
			sessionManger.setGlobalSessionTimeout(shiroProperties.getSessionTimeout() * 1000);
			Cookie cookie = new SimpleCookie("SHIROID");
			cookie.setHttpOnly(true);
			sessionManger.setSessionIdCookie(cookie);
			return sessionManger;
		}

		@Bean
		public CookieRememberMeManager remembermeManager() throws NoSuchAlgorithmException {
			SimpleCookie cookie = new SimpleCookie(CookieRememberMeManager.DEFAULT_REMEMBER_ME_COOKIE_NAME);
			cookie.setMaxAge(shiroProperties.getRemembermeCookieMaxAge());

			CookieRememberMeManager remembermeManager = new CookieRememberMeManager();
			// RemembermeManager默认使用AES(128)加解密
			remembermeManager.setCipherKey(Base64.decode("AF05JAuyuEB1ouJQ9Y9Phg=="));
			remembermeManager.setCookie(cookie);
			return remembermeManager;
		}

		@Bean
		public SecurityManager securityManager(ShiroJdbcRealm shiroJdbcRealm, RedisCacheManager redisCacheManager,
																					 DefaultWebSessionManager defaultWebSessoinManager,
																					 CookieRememberMeManager remembermeManager) {
			DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
			securityManager.setRealm(shiroJdbcRealm);
			securityManager.setCacheManager(redisCacheManager);
			securityManager.setSessionManager(defaultWebSessoinManager);
			securityManager.setRememberMeManager(remembermeManager);
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
			// 设为true即可使用Jdk动态代理，也可使用Cglib代理，否则无法拦截控制器的请求处理方法
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
