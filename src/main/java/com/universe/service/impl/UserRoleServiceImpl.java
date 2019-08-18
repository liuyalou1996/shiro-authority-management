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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universe.common.entity.po.UserRole;
import com.universe.mapper.UserRoleMapper;
import com.universe.service.UserRoleService;

/**
 * @author: liuyalou
 * @date: 2019年8月16日
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

  @Autowired
  private UserRoleMapper userRoleMapper;

  @Override
  public Integer playRole(UserRole userRole) {
    return userRoleMapper.insertUserRole(userRole);
  }

  @Override
  public Integer deleteUserRoleById(Integer id) {
    return userRoleMapper.deleteUserRoleById(id);
  }

}
