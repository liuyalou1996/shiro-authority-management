package com.universe.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.universe.common.entity.domain.UserDo;
import com.universe.common.entity.dto.GenericResponseDto;
import com.universe.service.UserService;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/user/add")
  @ResponseBody
  @RequiresPermissions("user:add")
  public GenericResponseDto addUser(@RequestBody UserDo user) {
    GenericResponseDto response = new GenericResponseDto();
    Integer result = userService.saveUser(user);

    if (result != null && result > 0) {
      response.setResultCode(0);
    } else {
      response.setResultCode(1);
    }

    return response;
  }

}
