package com.universe.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.universe.mapper.RoleMapper;
import com.universe.pojo.domain.RoleDo;
import com.universe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<BaseMapper<RoleDo>, RoleDo> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public IPage<RoleDo> listRoles() {
		return null;
	}

	@Override
	public List<String> getRolesByUsername(String username) {
		return roleMapper.getRolesByUsername(username);
	}

	@Override
	public boolean saveRole(RoleDo role) {
		return super.save(role);
	}

	@Override
	public boolean updateRoleByRoleId(RoleDo role) {
		return super.updateById(role);
	}

	@Override
	public boolean removeRoleByRoleId(int roleId) {
		return super.removeById(roleId);
	}
}
