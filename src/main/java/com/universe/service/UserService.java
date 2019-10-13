package com.universe.service;

import java.util.List;

import com.universe.common.entity.domain.User;

public interface UserService {

  List<User> listUsers();

  User getUserByUsername(String username);

  Integer saveUser(User user);

  Integer updateUser(User user);

  Integer removeUserByUserId(Integer userId);
}
