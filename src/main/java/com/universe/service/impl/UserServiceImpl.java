package com.universe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universe.common.entity.domain.User;
import com.universe.mapper.UserMapper;
import com.universe.service.UserService;

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
  public Integer saveUser(User user) {
    return userMapper.saveUser(user);
  }

  @Override
  public Integer updateUser(User user) {
    return userMapper.updateUser(user);
  }

  @Override
  public Integer removeUserByUserId(Integer userId) {
    return userMapper.removeUserByUserId(userId);
  }

}
