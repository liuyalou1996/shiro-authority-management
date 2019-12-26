package com.universe.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.universe.pojo.dto.response.GenericResponseDto;

/**
 * 页面跳转控制器
 * @author: liuyalou
 * @date: 2019年8月16日
 */
@Controller
public class PageDispatchingController {

  @RequestMapping("/auth/login")
  public String dispatchToLoginPage() {
    return "login";
  }

  @RequestMapping("/unauthorized")
  @ResponseBody
  public String dispatchToUnauthrorizedPage() {
    GenericResponseDto response = new GenericResponseDto();
    response.setResultCode(401);
    response.setResultDesc("无权限访问");
    return "unauthorized";
  }
}
