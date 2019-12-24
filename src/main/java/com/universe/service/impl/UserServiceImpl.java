package com.universe.service.impl;

import java.util.List;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universe.common.util.PasswordGenerationUtils;
import com.universe.mapper.UserDoMapper;
import com.universe.pojo.domain.UserDo;
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
    if (user == null) {
      return 0;
    }

    String salt = PasswordGenerationUtils.generatePasswordSalt();
    String hashedPassword = PasswordGenerationUtils.generateHashedPassword(user.getPassword(), salt);
    user.setSalt(salt);
    user.setPassword(hashedPassword);
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

  public static void main(String[] args) {
    String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
    String hashedPassword = PasswordGenerationUtils.generateHashedPassword("guest", salt);
    System.out.println(salt);
    System.out.println(hashedPassword);
  }

}
