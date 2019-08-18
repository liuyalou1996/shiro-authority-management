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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author: liuyalou
 * @date: 2019年8月13日
 */
public class Permission {

  private Integer permId;
  private String permName;
  private String description;
  private String createTime;
  private String updateTime;

  public Integer getPermId() {
    return permId;
  }

  public String getPermName() {
    return permName;
  }

  public String getDescription() {
    return description;
  }

  public String getCreateTime() {
    return createTime;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setPermId(Integer permId) {
    this.permId = permId;
  }

  public void setPermName(String permName) {
    this.permName = permName;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
