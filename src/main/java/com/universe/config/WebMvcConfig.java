package com.universe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import com.universe.common.holder.WebApplicationContextHolder;

/**
 * 消息转换器以及Thymeleaf试图解析器配置，通过application.properties配置Thymeleaf更方便
 * @author liuyalou
 * @date 2019年8月11日
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/mms/images/**").addResourceLocations("/mms/images/");
    registry.addResourceHandler("/mms/css/**").addResourceLocations("/mms/css/");
    registry.addResourceHandler("/mms/js/**").addResourceLocations("/mms/js/");
  }

  @Bean
  public SpringResourceTemplateResolver defaultTemplateResolver() {
    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
    templateResolver.setApplicationContext(WebApplicationContextHolder.getWebApplicationContext());
    templateResolver.setPrefix("classpath:templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    // 修改时自动更新
    templateResolver.setCacheable(false);
    return templateResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver) {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    templateEngine.setEnableSpringELCompiler(false);
    return templateEngine;
  }

  @Bean
  public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine);
    viewResolver.setCharacterEncoding("UTF-8");
    viewResolver.setContentType(MediaType.TEXT_HTML_VALUE);
    viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 5);
    return viewResolver;
  }

}
