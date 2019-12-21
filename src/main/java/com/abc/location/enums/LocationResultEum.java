package com.abc.location.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/20 12:21
 */
@Getter
@AllArgsConstructor
public enum LocationResultEum {
  PARAM_ILLEGAL(10001,"参数非法");
  private int code;
  private String msg;
}
