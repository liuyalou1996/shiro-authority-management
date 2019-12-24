package com.universe.web.exception.handler;

import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.universe.pojo.dto.GenericResponseDto;

/**
 * 主要处理Shiro相关的授权异常
 * @author: liuyalou
 * @date: 2019年8月20日
 */
@ControllerAdvice
public class ControllerExceptionHanlder {

  @ExceptionHandler(ShiroException.class)
  @ResponseBody
  public GenericResponseDto handleControllerException(ShiroException exception) {
    GenericResponseDto response = new GenericResponseDto();
    response.setResultCode(1);
    response.setResultDesc(exception.getMessage());
    return response;
  }
}
