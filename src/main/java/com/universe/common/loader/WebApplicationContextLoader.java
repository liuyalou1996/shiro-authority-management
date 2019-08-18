package com.universe.common.loader;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.universe.common.holder.WebApplicationContextHolder;

@Component
public class WebApplicationContextLoader implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

  }

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    WebApplicationContextHolder.initWebApplicationContext(applicationContext);
  }

}
