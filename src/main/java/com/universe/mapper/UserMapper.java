package com.universe.mapper;

import java.util.List;

import com.universe.common.entity.domain.User;

public interface UserMapper {

  List<User> listUsers();

  User getUserByUsername(String username);

  Integer saveUser(User user);

  Integer updateUser(User user);

  Integer removeUserByUserId(Integer userId);
}
