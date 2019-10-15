package com.universe.common.entity.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserDo {

  private Integer userId;
  private String username;
  private String password;
  private String salt;
  private String status;
  private Date createTime;
  private Date updateTime;

  public Integer getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getSalt() {
    return salt;
  }

  public String getStatus() {
    return status;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
