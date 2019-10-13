package com.universe.example;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordGenerationExample {

  public static String generateSalt() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public static String generateHashedPassword(String rawPassword, String salt) {
    SimpleHash hash = new SimpleHash(Md5Hash.ALGORITHM_NAME, rawPassword, salt, 2);
    return hash.toString();
  }

  public static void main(String[] args) {
    
  }
}
