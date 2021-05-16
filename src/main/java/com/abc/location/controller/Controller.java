package com.abc.location.controller;

import com.abc.location.annotation.Log;
import com.abc.location.common.CommonResponse;
import com.abc.location.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/21 14:11
 */
@RestController
@RequestMapping("/location")
public class Controller {
  @Resource
  private LocationService locationService;

  @GetMapping("/query")
  @Log()
  public CommonResponse selectLocationByCode(
      @RequestParam(name = "code", required = false) Integer code,
      @RequestParam(name = "name", required = false, defaultValue = "") String name) {
    return locationService.selectLocation(code, name);
  }
}
