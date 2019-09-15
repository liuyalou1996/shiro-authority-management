package com.universe.common.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;

@Component
public class ResourceReleaseApplicationListener implements ApplicationListener<ContextClosedEvent> {

  @Override
  public void onApplicationEvent(ContextClosedEvent event) {
    ApplicationContext context = event.getApplicationContext();
    JedisPool jedisPool = context.getBean(JedisPool.class);
    if (!jedisPool.isClosed()) {
      jedisPool.close();
    }
  }

}
