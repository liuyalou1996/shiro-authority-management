package com.universe.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.universe.mapper.UserMapper;
import com.universe.pojo.domain.UserDo;
import com.universe.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<BaseMapper<UserDo>, UserDo> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public Page<UserDo> listUsers(int pageNum, int pageSize, String username) {
		Wrapper<UserDo> wrapper = null;
		if (StringUtils.isNotBlank(username)) {
			wrapper = Wrappers.<UserDo>lambdaQuery().eq(UserDo::getUsername, username);
		}

		return userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
	}

	@Override
	public UserDo getUserByUsername(String username) {
		return super.getOne(Wrappers.<UserDo>lambdaQuery().eq(UserDo::getUsername, username));
	}

	@Override
	public boolean saveUser(UserDo user) {
		return super.save(user);
	}

	@Override
	public boolean updateUserByUserId(UserDo user) {
		return super.updateById(user);
	}

	@Override
	public boolean removeUserByUserId(long userId) {
		return super.removeById(userId);
	}

}
