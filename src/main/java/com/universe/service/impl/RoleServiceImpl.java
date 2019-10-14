package com.universe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universe.common.entity.domain.RoleDo;
import com.universe.mapper.RoleDoMapper;
import com.universe.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleDoMapper roleMapper;

  @Override
  public List<RoleDo> listRoles() {
    return roleMapper.listRoles();
  }

  @Override
  public List<RoleDo> getRolesByUsername(String username) {
    return roleMapper.getRolesByUsername(username);
  }

  @Override
  public Integer saveRole(RoleDo role) {
    return roleMapper.saveRole(role);
  }

  @Override
  public Integer updateRole(RoleDo role) {
    return roleMapper.updateRole(role);
  }

  @Override
  public Integer removeRoleByRoleId(Integer roleId) {
    return roleMapper.removeRoleByRoleId(roleId);
  }

}
