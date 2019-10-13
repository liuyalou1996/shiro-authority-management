package com.universe.mapper;

import java.util.List;

import com.universe.common.entity.domain.Role;

public interface RoleMapper {

  List<Role> listRoles();

  List<Role> getRolesByUsername(String username);

  Integer saveRole(Role role);

  Integer updateRole(Role role);

  Integer removeRoleByRoleId(Integer roleId);
}
