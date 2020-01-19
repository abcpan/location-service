package com.abc.location.controller;

import com.abc.location.common.CommonResponse;
import com.abc.location.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/21 14:11
 */
@RestController
@RequestMapping("/location")
@Slf4j
public class Controller {
  @Autowired
  private LocationService locationService;
  @GetMapping("/query")
  public CommonResponse selectLocationByCode(
          @RequestParam(name="code",required = false) Integer code,
          @RequestParam(name="name",required = false) String name){
    return locationService.selectLocation(code,name);
  }
}
