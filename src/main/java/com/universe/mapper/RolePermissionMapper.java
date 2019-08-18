package com.universe.mapper;

import com.universe.common.entity.po.RolePermission;

/**
 * @author liuyalou
 * @date 2019年8月13日
 */
public interface RolePermissionMapper {

  Integer insertRolePermission(RolePermission rolePermission);

  Integer deleteRolePermission(Integer id);
}
