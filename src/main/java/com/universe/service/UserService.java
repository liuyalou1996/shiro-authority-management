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

import com.universe.common.entity.po.User;

/**
 * @author: liuyalou
 * @date: 2019年8月14日
 */
public interface UserService {

  List<User> listUsers();

  User getUserByUsername(String username);

  Integer insertUser(User user);

  Integer updateUser(User user);

  Integer deleteUserByUserId(Integer userId);
}
