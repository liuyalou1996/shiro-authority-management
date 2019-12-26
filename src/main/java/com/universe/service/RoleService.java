package com.universe.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.universe.pojo.domain.RoleDo;

import java.util.List;

public interface RoleService {

	IPage<RoleDo> listRoles();

	List<String> getRolesByUsername(String username);

	boolean saveRole(RoleDo role);

	boolean updateRoleByRoleId(RoleDo role);

	boolean removeRoleByRoleId(int roleId);

}
