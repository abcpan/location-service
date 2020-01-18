package com.abc.location.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/21 15:39
 */
@Data
@NoArgsConstructor
public class LocationDTO implements Serializable {
  private Integer code;
  private String name;
}
