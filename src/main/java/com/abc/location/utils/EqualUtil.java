package com.abc.location.utils;

/**
 * @author abc
 * @version V1.0
 * @date 2021/12/12 01:21
 */
public class EqualUtil {
  public static <T> boolean isEqual(T a, T b) {
    if(a == null) {
      return b == null;
    }
    return a.equals(b);
  }
}
