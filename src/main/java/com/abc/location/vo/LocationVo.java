package com.abc.location.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/21 15:39
 */
@Data
@NoArgsConstructor
public class LocationVo implements Serializable {
  private Integer code;
  private String name;
  private int type;
  private Integer parentCode;

  private List<LocationVo> children = new ArrayList<>();
}
