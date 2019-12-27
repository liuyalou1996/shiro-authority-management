package com.universe.web.exception.handler;

import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.universe.pojo.dto.response.GenericResponseDto;

/**
 * 统一异常处理
 * @author 刘亚楼
 * @date 2019/12/27
 */
@ControllerAdvice
public class ControllerExceptionHanlder {

	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public GenericResponseDto<?> handleControllerException(ShiroException exception) {
		return GenericResponseDto.builder()
      .resultCode(0)
      .resultDesc(exception.getMessage())
      .build();
	}
}
