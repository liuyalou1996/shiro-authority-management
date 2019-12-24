package com.universe.pojo.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RoleDo {

  private Integer roleId;
  private String roleName;
  private String description;
  private Date createTime;
  private Date updateTime;

  public Integer getRoleId() {
    return roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public String getDescription() {
    return description;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public void setDescription(String description) {
    this.description = description;
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
