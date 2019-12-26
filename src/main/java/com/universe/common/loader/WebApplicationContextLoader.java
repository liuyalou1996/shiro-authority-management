package com.universe.common.loader;

import com.universe.common.holder.WebApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * web应用上下文加载器，实现BeanDefinitionRegistryPostProcessor获取最高优先级
 * @author: liuyalou
 * @date: 2019年10月11日
 */
@Component
public class WebApplicationContextLoader implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebApplicationContextLoader.class);

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		LOGGER.info("Initializing web application context!");
		WebApplicationContextHolder.initWebApplicationContext(applicationContext);
	}

}
