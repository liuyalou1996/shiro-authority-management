package com.universe.service.impl;

import com.universe.mapper.RoleMapper;
import com.universe.pojo.domain.RoleDo;
import com.universe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<RoleDo> listRoles() {
		return null;
	}

	@Override
	public List<RoleDo> getRolesByUsername(String username) {
		return null;
	}

	@Override
	public Integer saveRole(RoleDo role) {
		return roleMapper.insert(role);
	}

	@Override
	public Integer updateRole(RoleDo role) {
		return roleMapper.updateById(role);
	}

	@Override
	public Integer removeRoleByRoleId(Integer roleId) {
		return roleMapper.deleteById(roleId);
	}
}
