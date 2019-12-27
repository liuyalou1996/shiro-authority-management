package com.universe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.universe.pojo.domain.UserDo;

public interface UserService {

	Page<UserDo> listUsers(int pageNum, int pageSize, String username);

	UserDo getUserByUsername(String username);

	boolean saveUser(UserDo user);

	boolean updateUserByUserId(UserDo user);

	boolean removeUserByUserId(long userId);

}
