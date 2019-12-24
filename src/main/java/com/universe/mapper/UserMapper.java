package com.universe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.pojo.domain.UserDo;

public interface UserMapper extends BaseMapper<UserDo> {

	UserDo getUserByUsername(String username);
}
