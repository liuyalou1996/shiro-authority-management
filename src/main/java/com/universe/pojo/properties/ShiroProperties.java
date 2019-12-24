/*
 * Copyright (C) 2011-2019 ShenZhen iBOXCHAIN Information Technology Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary
 * information of iBoxChain Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with iBoxchain inc.
 */
package com.universe.pojo.properties;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.druid.util.StringUtils;

/**
 * @author: liuyalou
 * @date: 2019年8月14日
 */
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {

  private String anonUrl;
  private String loginUrl;
  private String authUrl;
  private String successUrl;
  private String logoutUrl;
  private String unauthorizedUrl;
  private Integer remembermeCookieMaxAge;
  private Long sessionTimeout;

  public String getAnonUrl() {
    return anonUrl;
  }

  public String getLoginUrl() {
    return loginUrl;
  }

  public String getAuthUrl() {
    return authUrl;
  }

  public String getSuccessUrl() {
    return successUrl;
  }

  public String getLogoutUrl() {
    return logoutUrl;
  }

  public String getUnauthorizedUrl() {
    return unauthorizedUrl;
  }

  public List<String> getAnonUrlList() {
    return StringUtils.isEmpty(this.anonUrl) ? Collections.emptyList() : Arrays.asList(this.anonUrl.split(","));
  }

  public void setAnonUrl(String anonUrl) {
    this.anonUrl = anonUrl;
  }

  public void setLoginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
  }

  public void setAuthUrl(String authUrl) {
    this.authUrl = authUrl;
  }

  public void setSuccessUrl(String successUrl) {
    this.successUrl = successUrl;
  }

  public void setLogoutUrl(String logoutUrl) {
    this.logoutUrl = logoutUrl;
  }

  public void setUnauthorizedUrl(String unauthorizedUrl) {
    this.unauthorizedUrl = unauthorizedUrl;
  }

  public Integer getRemembermeCookieMaxAge() {
    return remembermeCookieMaxAge;
  }

  public Long getSessionTimeout() {
    return sessionTimeout;
  }

  public void setRemembermeCookieMaxAge(Integer remembermeCookieMaxAge) {
    this.remembermeCookieMaxAge = remembermeCookieMaxAge;
  }

  public void setSessionTimeout(Long sessionTimeout) {
    this.sessionTimeout = sessionTimeout;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
