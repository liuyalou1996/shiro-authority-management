package com.universe.web.controller.system;

import com.universe.common.constant.Permissions;
import com.universe.common.util.ShiroUtils;
import com.universe.pojo.dto.response.MenuInfoRespDto;
import com.universe.service.ResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@RequiresPermissions(Permissions.Resosurce.RESOURCE_MENU_FETCH)
	@GetMapping(value = "/menu")
	public List<MenuInfoRespDto> getMenuByUsername() {
		String username = (String) ShiroUtils.getSubject().getPrincipal();
		return resourceService.getMenuByUsername(username);
	}
}
