package com.universe.mapper;

import java.util.List;

import com.universe.common.entity.po.Role;

/**
 * @author liuyalou
 * @date 2019年8月13日
 */
public interface RoleMapper {

  List<Role> listRoles();

  List<Role> getRolesByUsername(String username);

  Integer insertRole(Role role);

  Integer updateRole(Role role);

  Integer deleteRoleByRoleId(Integer roleId);
}
