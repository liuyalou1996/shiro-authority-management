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
package com.universe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universe.common.entity.po.Role;
import com.universe.mapper.RoleMapper;
import com.universe.service.RoleService;

/**
 * @author: liuyalou
 * @date: 2019年8月15日
 */
@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleMapper roleMapper;

  @Override
  public List<Role> listRoles() {
    return roleMapper.listRoles();
  }

  @Override
  public List<Role> getRolesByUsername(String username) {
    return roleMapper.getRolesByUsername(username);
  }

  @Override
  public Integer insertRole(Role role) {
    return roleMapper.insertRole(role);
  }

  @Override
  public Integer updateRole(Role role) {
    return roleMapper.updateRole(role);
  }

  @Override
  public Integer deleteRole(Integer roleId) {
    return roleMapper.deleteRoleByRoleId(roleId);
  }

}
