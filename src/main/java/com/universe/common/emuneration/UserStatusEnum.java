package com.universe.common.emuneration;

public enum UserStatusEnum {

	/**
	 * 禁用
	 */
	NORMAL("0"),
	/**
	 * 启用
	 */
	FORBIDDEN("1"),
	/**
	 * 锁定
	 */
	LOCKED("2");

	private String value;

	private UserStatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
