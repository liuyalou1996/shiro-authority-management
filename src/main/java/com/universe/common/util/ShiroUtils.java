package com.universe.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public abstract class ShiroUtils {

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static void setSessionAttribute(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(String key) {
		return getSession().getAttribute(key);
	}

	public static <T> T getSessionAttribute(String key, Class<T> clazz) {
		Object value = getSession().getAttribute(key);
		return value == null ? null : clazz.cast(value);
	}

	public static void removeSessionAttribute(String key) {
		getSession().removeAttribute(key);
	}

}
