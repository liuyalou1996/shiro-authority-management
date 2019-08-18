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

import com.universe.common.entity.po.Permission;
import com.universe.mapper.PermissionMapper;
import com.universe.service.PermissionService;

/**
 * @author: liuyalou
 * @date: 2019年8月15日
 */
@Service
public class PermissionServiceImpl implements PermissionService {

  @Autowired
  private PermissionMapper permissionMapper;

  @Override
  public List<Permission> listPermissions() {
    return permissionMapper.listPermissions();
  }

  @Override
  public List<Permission> getPermissionsByUsername(String username) {
    return permissionMapper.getPermissionsByUsername(username);
  }

  @Override
  public Integer insertPermission(Permission permission) {
    return permissionMapper.insertPermission(permission);
  }

  @Override
  public Integer updatePermission(Permission permission) {
    return permissionMapper.updatePermission(permission);
  }

  @Override
  public Integer deletePermissionByPermId(Integer permId) {
    return permissionMapper.deletePermissionByPermId(permId);
  }

}
