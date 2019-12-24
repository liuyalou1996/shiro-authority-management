package com.universe.service;

import java.util.List;

import com.universe.pojo.domain.UserDo;

public interface UserService {

  List<UserDo> listUsers();

  UserDo getUserByUsername(String username);

  Integer saveUser(UserDo user);

  Integer updateUser(UserDo user);

  Integer removeUserByUserId(Integer userId);
}
