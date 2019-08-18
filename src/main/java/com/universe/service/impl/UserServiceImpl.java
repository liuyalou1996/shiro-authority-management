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

import com.universe.common.entity.po.User;
import com.universe.mapper.UserMapper;
import com.universe.service.UserService;

/**
 * @author: liuyalou
 * @date: 2019年8月15日
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public List<User> listUsers() {
    return userMapper.listUsers();
  }

  @Override
  public User getUserByUsername(String username) {
    return userMapper.getUserByUsername(username);
  }

  @Override
  public Integer insertUser(User user) {
    return userMapper.insertUser(user);
  }

  @Override
  public Integer updateUser(User user) {
    return userMapper.updateUser(user);
  }

  @Override
  public Integer deleteUserByUserId(Integer userId) {
    return userMapper.deleteUserByUserId(userId);
  }

}
