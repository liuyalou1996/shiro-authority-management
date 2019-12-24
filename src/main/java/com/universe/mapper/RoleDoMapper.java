package com.universe.mapper;

import java.util.List;

import com.universe.pojo.domain.RoleDo;

public interface RoleDoMapper {

  List<RoleDo> listRoles();

  List<RoleDo> getRolesByUsername(String username);

  Integer saveRole(RoleDo role);

  Integer updateRole(RoleDo role);

  Integer removeRoleByRoleId(Integer roleId);
}
