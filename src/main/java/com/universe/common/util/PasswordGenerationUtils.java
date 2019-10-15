package com.universe.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public abstract class PasswordGenerationUtils {

  /**
   * 密码散列时用的散列算法
   */
  public static final String HASH_ALGORITHM = Md5Hash.ALGORITHM_NAME;

  /**
   * 散列次数
   */
  public static final int HASH_ITERATIONS = 2;

  /**
   * 明文密码与盐值混合生成散列密码
   * @param clearPassword 明文密码
   * @param salt 盐值
   * @return 散列后的密码
   */
  public static String generateHashedPassword(String clearPassword, String salt) {
    if (StringUtils.isBlank(clearPassword)) {
      return null;
    }

    SimpleHash simpleHash = new SimpleHash(HASH_ALGORITHM, clearPassword, salt, HASH_ITERATIONS);
    return simpleHash.toHex();
  }

  public static String generatePasswordSalt() {
    return new SecureRandomNumberGenerator().nextBytes().toHex();
  }
}
