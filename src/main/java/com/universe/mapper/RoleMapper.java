package com.universe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.pojo.domain.RoleDo;

import java.util.List;

public interface RoleMapper extends BaseMapper<RoleDo> {

	List<RoleDo> getRolesByUsername(String username);
}
