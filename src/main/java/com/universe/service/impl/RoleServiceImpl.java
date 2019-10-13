package com.universe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universe.common.entity.domain.Role;
import com.universe.mapper.RoleMapper;
import com.universe.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleMapper roleMapper;

  @Override
  public List<Role> listRoles() {
    return roleMapper.listRoles();
  }

  @Override
  public List<Role> getRolesByUsername(String username) {
    return roleMapper.getRolesByUsername(username);
  }

  @Override
  public Integer saveRole(Role role) {
    return roleMapper.saveRole(role);
  }

  @Override
  public Integer updateRole(Role role) {
    return roleMapper.updateRole(role);
  }

  @Override
  public Integer removeRoleByRoleId(Integer roleId) {
    return roleMapper.removeRoleByRoleId(roleId);
  }

}
