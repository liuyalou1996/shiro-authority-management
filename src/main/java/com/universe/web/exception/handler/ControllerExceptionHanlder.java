package com.universe.web.exception.handler;

import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.universe.pojo.dto.response.GenericRespDto;

/**
 * 统一异常处理
 * @author 刘亚楼
 * @date 2019/12/27
 */
@ControllerAdvice
public class ControllerExceptionHanlder {

	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public GenericRespDto<?> handleControllerException(ShiroException exception) {
		return GenericRespDto.builder()
			.resultCode(0)
			.resultDesc(exception.getMessage())
			.build();
	}
}
