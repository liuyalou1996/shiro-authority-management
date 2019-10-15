package com.universe.common.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author: liuyalou
 * @date: 2019年6月17日
 */
public abstract class ValidationUtils {

  public static boolean isEmpty(Object obj) throws IllegalArgumentException {
    if (Objects.isNull(obj)) {
      return true;
    }

    if (obj instanceof String) {
      if (StringUtils.isEmpty((String) obj)) {
        return true;
      }
    } else if (obj instanceof Collection) {
      if (CollectionUtils.isEmpty((Collection<?>) obj)) {
        return true;
      }
    } else if (obj instanceof Map) {
      if (CollectionUtils.isEmpty((Map<?, ?>) obj)) {
        return true;
      }
    } else if (obj.getClass().isArray()) {
      if (Array.getLength(obj) == 0) {
        return true;
      }
    }

    return false;
  }

  public static boolean isNotEmpty(Object obj) {
    return !isEmpty(obj);
  }

  public static boolean isBlank(String str) {
    return StringUtils.isBlank(str);
  }

  public static boolean isNotBlank(String str) {
    return StringUtils.isNotBlank(str);
  }

  public static boolean equals(String former, String latter) {
    return StringUtils.equals(former, latter);
  }

  public static boolean notEquals(String former, String latter) {
    return StringUtils.equals(former, latter);
  }

}
