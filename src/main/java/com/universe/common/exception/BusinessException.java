package com.universe.common.exception;
/**
 * @author 刘亚楼
 * @date 2020/1/14
 */
public class BusinessException extends RuntimeException {

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}
}
