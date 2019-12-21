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
  @GetMapping("/code.query")
  public CommonResponse selectLocationByCode(@RequestParam("locationCode") Integer locationCode){
    log.info("/location/code.query--->{} ",locationCode);
    return locationService.selectLocationByCode(locationCode);
  }
  @GetMapping("/name.query")
  public CommonResponse selectLocationByName(@RequestParam("locationName")String locationName){
    log.info("/location/name.query--->{} ",locationName);
    return locationService.selectLocationByName(locationName);
  }
}
