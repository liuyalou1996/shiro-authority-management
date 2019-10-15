
package com.universe.config.shiro.serializer;

import org.crazycake.shiro.exception.SerializationException;
import org.crazycake.shiro.serializer.StringSerializer;

import com.universe.common.util.ProtostuffUtils;

/**
 * 由于整合Shiro的redis开源插件，自定义key的序列化器必须继承StringSerializer
 * @author: liuyalou
 * @date: 2019年10月15日
 */
public class ProtostuffStringSerializer extends StringSerializer {

  @Override
  public byte[] serialize(String t) throws SerializationException {
    return ProtostuffUtils.serialize(t);
  }

  @Override
  public String deserialize(byte[] bytes) throws SerializationException {
    return ProtostuffUtils.deserialize(bytes, String.class);
  }

}
