package com.universe.web.controller;

import com.universe.common.constant.Permissions;
import com.universe.pojo.domain.UserDo;
import com.universe.pojo.dto.response.GenericResponseDto;
import com.universe.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	@ResponseBody
	@RequiresPermissions(Permissions.User.USER_ADD)
	public GenericResponseDto<?> addUser(@RequestBody UserDo user) {
		return null;
	}

}
