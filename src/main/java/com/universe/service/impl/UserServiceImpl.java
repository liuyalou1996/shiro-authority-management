package com.universe.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universe.common.entity.domain.UserDo;
import com.universe.mapper.UserDoMapper;
import com.universe.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDoMapper userMapper;

  @Override
  public List<UserDo> listUsers() {
    return userMapper.listUsers();
  }

  @Override
  public UserDo getUserByUsername(String username) {
    return userMapper.getUserByUsername(username);
  }

  @Override
  public Integer saveUser(UserDo user) {
    if (user == null || StringUtils.isBlank(user.getUsername())) {
      return 0;
    }

    return userMapper.saveUser(user);
  }

  @Override
  public Integer updateUser(UserDo user) {
    return userMapper.updateUser(user);
  }

  @Override
  public Integer removeUserByUserId(Integer userId) {
    return userMapper.removeUserByUserId(userId);
  }

  public String generateHashedPassword(String rawPassowrd, String salt) {
    return null;
  }

}
