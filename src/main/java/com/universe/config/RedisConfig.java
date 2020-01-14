package com.universe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * @author 刘亚楼
 * @date 2020/1/14
 */
@Configuration
public class RedisConfig {

	/**
	 * 自动配置时会注入到容器中
	 */
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;

	@Bean
	public JedisPool jedisPool() {
		JedisPoolConfig poolConfig = (JedisPoolConfig) jedisConnectionFactory.getPoolConfig();
		String host = jedisConnectionFactory.getHostName();
		int port = jedisConnectionFactory.getPort();
		int timeout = jedisConnectionFactory.getTimeout();
		String password = jedisConnectionFactory.getPassword();
		return new JedisPool(poolConfig, host, port, timeout, password);
	}

}
