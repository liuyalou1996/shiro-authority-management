package com.universe.mapper;

import java.util.List;

import com.universe.common.entity.po.Permission;

/**
 * @author liuyalou
 * @date 2019年8月13日
 */
public interface PermissionMapper {

  List<Permission> listPermissions();

  List<Permission> getPermissionsByUsername(String username);

  Integer insertPermission(Permission permission);

  Integer updatePermission(Permission permission);

  Integer deletePermissionByPermId(Integer permId);
}
