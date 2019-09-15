package com.universe.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.universe.common.entity.properties.RedisProperties;
import com.universe.common.entity.properties.RedisProperties.ConnectionPoolProperties;
import com.universe.common.entity.properties.RedisProperties.ServerProperties;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:redis/redis.properties")
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

  @Bean
  public JedisPoolConfig jedisPoolConfig(RedisProperties redisProperties) {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    ConnectionPoolProperties properties = redisProperties.getPool();
    jedisPoolConfig.setMaxTotal(properties.getMaxTotal());
    jedisPoolConfig.setMaxIdle(properties.getMaxIdle());
    jedisPoolConfig.setMinIdle(properties.getMinIdle());
    jedisPoolConfig.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
    jedisPoolConfig.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
    jedisPoolConfig.setMaxWaitMillis(properties.getMaxWaitMillis());
    jedisPoolConfig.setNumTestsPerEvictionRun(properties.getNumTestsPerEvictionRun());
    jedisPoolConfig.setTestOnBorrow(properties.isTestOnBorrow());
    jedisPoolConfig.setTestOnReturn(properties.isTestOnReturn());
    jedisPoolConfig.setTestWhileIdle(properties.isTestWhileIdle());
    return jedisPoolConfig;
  }

  @Bean
  public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig, RedisProperties redisProperties) {
    ServerProperties properteis = redisProperties.getServer();
    String host = properteis.getHost();
    int port = properteis.getPort();
    String password = properteis.getPassword();
    int timeout = properteis.getTimeout();

    JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
    return jedisPool;
  }

}
