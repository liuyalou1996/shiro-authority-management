package com.universe.web.exception.handler;

import com.universe.pojo.dto.response.GenericRespDto;
import com.universe.pojo.dto.response.GenericRespDto.Builder;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.UnknownSessionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @author 刘亚楼
 * @date 2019/12/27
 */
@ControllerAdvice
public class ControllerExceptionHanlder {

	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public GenericRespDto<?> handleShiroException(ShiroException exception) {
		return GenericRespDto.builder().resultCode(0).resultDesc(exception.getMessage()).build();
	}

	@ExceptionHandler(CredentialsException.class)
	@ResponseBody
	public GenericRespDto<?> handleCredentialsException(CredentialsException exception) {
		Builder<?> builder = GenericRespDto.builder().resultCode(0);
		if (exception instanceof IncorrectCredentialsException) {
			builder.resultDesc("密码错误!");
		} else if (exception instanceof ExpiredCredentialsException) {
			builder.resultDesc("密码过期!");
		}

		return builder.build();
	}

	@ExceptionHandler(SessionException.class)
	@ResponseBody
	public GenericRespDto<?> handleShiroException(SessionException exception) {
		Builder<?> builder = GenericRespDto.builder().resultCode(0);
		if (exception instanceof UnknownSessionException) {
			builder.resultDesc("未知会话!");
		} else if (exception instanceof ExpiredSessionException) {
			builder.resultDesc("会话已过期!");
		}

		return builder.build();
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseBody
	public GenericRespDto<?> handleShiroException(UnauthorizedException exception) {
		return GenericRespDto.builder().resultCode(0).resultDesc("无权限访问!").build();
	}
}
