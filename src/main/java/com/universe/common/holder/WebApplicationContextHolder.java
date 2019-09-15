package com.universe.common.holder;

import org.springframework.context.ApplicationContext;

public abstract class WebApplicationContextHolder {

  private static ApplicationContext context;

  public static void initWebApplicationContext(ApplicationContext context) {
    WebApplicationContextHolder.context = context;
  }

  public static ApplicationContext getWebApplicationContext() {
    return context;
  }

  public static <T> T getBean(Class<T> clazz) {
    return context.getBean(clazz);
  }

  public static <T> T getBean(String name, Class<T> clazz) {
    return context.getBean(name, clazz);
  }
}
