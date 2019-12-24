package com.universe.service.impl;

import com.universe.mapper.UserMapper;
import com.universe.pojo.domain.UserDo;
import com.universe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserDo> listUsers() {
		return null;
	}

	@Override
	public UserDo getUserByUsername(String username) {
		return null;
	}

	@Override
	public Integer saveUser(UserDo user) {
		return null;
	}

	@Override
	public Integer updateUser(UserDo user) {
		return null;
	}

	@Override
	public Integer removeUserByUserId(Integer userId) {
		return null;
	}
}
