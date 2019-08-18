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
package com.universe.service;

import java.util.List;

import com.universe.common.entity.po.Role;

/**
 * @author: liuyalou
 * @date: 2019年8月14日
 */
public interface RoleService {

  List<Role> listRoles();

  List<Role> getRolesByUsername(String username);

  Integer insertRole(Role role);

  Integer updateRole(Role role);

  Integer deleteRole(Integer roleId);

}
