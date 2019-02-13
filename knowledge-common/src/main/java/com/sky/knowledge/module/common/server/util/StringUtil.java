package com.sky.knowledge.module.common.server.util;

import java.math.BigDecimal;

public class StringUtil
{
  public static String BigDecimalToString(BigDecimal bd)
  {
    if (bd == null) {
      return null;
    }
    return bd.toString();
  }
}