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
package com.universe.common.entity.po;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author: liuyalou
 * @date: 2019年8月13日
 */
public class RolePermission {

  private Integer id;
  private Integer roleId;
  private Integer permisionId;
  private Date createTime;

  public Integer getId() {
    return id;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public Integer getPermisionId() {
    return permisionId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public void setPermisionId(Integer permisionId) {
    this.permisionId = permisionId;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
