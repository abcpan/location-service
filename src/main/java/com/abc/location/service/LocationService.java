package com.abc.location.service;

import com.abc.location.common.CommonResponse;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/20 11:15
 */
public interface LocationService {
  CommonResponse selectLocation(Integer code,String name);
}
