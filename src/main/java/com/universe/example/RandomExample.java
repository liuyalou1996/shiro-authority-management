/*
 * Copyright (C) 2011-2019 ShenZhen iBOXCHAIN Information Technology Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary
 * information of iBoxChain Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with iBoxchain inc.
 */
package com.universe.example;

import java.util.UUID;

/**
 * @author: liuyalou
 * @date: 2019年10月12日
 */
public class RandomExample {

  public static void main(String[] args) {
    System.out.println(UUID.randomUUID().toString().replace("-", ""));
  }
}
