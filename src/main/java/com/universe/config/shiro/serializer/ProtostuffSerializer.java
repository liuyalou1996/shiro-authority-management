
package com.universe.config.shiro.serializer;

import org.crazycake.shiro.exception.SerializationException;
import org.crazycake.shiro.serializer.RedisSerializer;

import com.universe.common.util.ProtostuffUtils;

public class ProtostuffSerializer implements RedisSerializer<Object> {

  @Override
  public byte[] serialize(Object t) throws SerializationException {
    return ProtostuffUtils.serialize(t);
  }

  @Override
  public Object deserialize(byte[] bytes) throws SerializationException {
    return ProtostuffUtils.deserialize(bytes, Object.class);
  }

}
