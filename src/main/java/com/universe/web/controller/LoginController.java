package com.universe.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.universe.common.entity.dto.GenericResponseDto;

@Controller
public class LoginController {

  @ResponseBody
  @PostMapping("/auth/login")
  public GenericResponseDto login(String username, String password) {
    UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
    Subject subject = SecurityUtils.getSubject();
    GenericResponseDto response = new GenericResponseDto();

    try {
      subject.login(token);
      response.setResultCode(0);
      response.setResultDesc("登录成功");
    } catch (AuthenticationException e) {
      response.setResultCode(1);
      response.setResultDesc(e.getMessage());
    }

    return response;
  }

}
