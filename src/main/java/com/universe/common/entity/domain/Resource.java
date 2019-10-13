package com.universe.common.entity.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Resource {

  private Integer resourceId;
  private Integer parentInd;
  private String resourceName;
  private String resourceUrl;
  private String resourceIcon;
  private String type;
  private Integer priority;
  private String status;
  private String permission;
  private Date createTime;
  private Date updateTime;

  public Integer getResourceId() {
    return resourceId;
  }

  public Integer getParentInd() {
    return parentInd;
  }

  public String getResourceName() {
    return resourceName;
  }

  public String getResourceUrl() {
    return resourceUrl;
  }

  public String getResourceIcon() {
    return resourceIcon;
  }

  public String getType() {
    return type;
  }

  public Integer getPriority() {
    return priority;
  }

  public String getStatus() {
    return status;
  }

  public String getPermission() {
    return permission;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setResourceId(Integer resourceId) {
    this.resourceId = resourceId;
  }

  public void setParentInd(Integer parentInd) {
    this.parentInd = parentInd;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public void setResourceUrl(String resourceUrl) {
    this.resourceUrl = resourceUrl;
  }

  public void setResourceIcon(String resourceIcon) {
    this.resourceIcon = resourceIcon;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setPermission(String permission) {
    this.permission = permission;
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
