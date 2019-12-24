
package com.universe.pojo.properties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

  private final ServerProperties server = new ServerProperties();

  private final ConnectionPoolProperties pool = new ConnectionPoolProperties();

  public ServerProperties getServer() {
    return server;
  }

  public ConnectionPoolProperties getPool() {
    return pool;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  public static class ServerProperties {

    private String host;
    private Integer port;
    private String password;
    private Integer timeout;

    public String getHost() {
      return host;
    }

    public Integer getPort() {
      return port;
    }

    public String getPassword() {
      return password;
    }

    public Integer getTimeout() {
      return timeout;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public void setPort(Integer port) {
      this.port = port;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public void setTimeout(Integer timeout) {
      this.timeout = timeout;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

  }

  public static class ConnectionPoolProperties {

    private Integer maxTotal;
    private Integer maxIdle;
    private Integer minIdle;
    private Long minEvictableIdleTimeMillis;
    private Long timeBetweenEvictionRunsMillis;
    private Long maxWaitMillis;
    private Integer numTestsPerEvictionRun;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean testWhileIdle;

    public Integer getMaxTotal() {
      return maxTotal;
    }

    public Integer getMaxIdle() {
      return maxIdle;
    }

    public Integer getMinIdle() {
      return minIdle;
    }

    public Long getMinEvictableIdleTimeMillis() {
      return minEvictableIdleTimeMillis;
    }

    public Long getTimeBetweenEvictionRunsMillis() {
      return timeBetweenEvictionRunsMillis;
    }

    public Long getMaxWaitMillis() {
      return maxWaitMillis;
    }

    public Integer getNumTestsPerEvictionRun() {
      return numTestsPerEvictionRun;
    }

    public boolean isTestOnBorrow() {
      return testOnBorrow;
    }

    public boolean isTestOnReturn() {
      return testOnReturn;
    }

    public boolean isTestWhileIdle() {
      return testWhileIdle;
    }

    public void setMaxTotal(Integer maxTotal) {
      this.maxTotal = maxTotal;
    }

    public void setMaxIdle(Integer maxIdle) {
      this.maxIdle = maxIdle;
    }

    public void setMinIdle(Integer minIdle) {
      this.minIdle = minIdle;
    }

    public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
      this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
      this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public void setMaxWaitMillis(Long maxWaitMillis) {
      this.maxWaitMillis = maxWaitMillis;
    }

    public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
      this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
      this.testOnBorrow = testOnBorrow;
    }

    public void setTestOnReturn(boolean testOnReturn) {
      this.testOnReturn = testOnReturn;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
      this.testWhileIdle = testWhileIdle;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

  }
}
