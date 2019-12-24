package com.universe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.universe.pojo.properties.RedisProperties;
import com.universe.pojo.properties.RedisProperties.ConnectionPoolProperties;
import com.universe.pojo.properties.RedisProperties.ServerProperties;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:redis/redis.properties")
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

  @Autowired
  private RedisProperties redisProperties;

  @Bean
  public JedisPoolConfig jedisPoolConfig() {
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
  public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
    ServerProperties properties = redisProperties.getServer();
    String host = properties.getHost();
    int port = properties.getPort();
    String password = properties.getPassword();
    int timeout = properties.getTimeout();

    JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
    return jedisPool;
  }

}
