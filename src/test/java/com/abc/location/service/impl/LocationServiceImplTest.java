package com.abc.location.service.impl;

import com.abc.location.common.CommonResponse;
import com.abc.location.model.Location;
import com.abc.location.service.LocationService;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/21 11:23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class LocationServiceImplTest {
  @Autowired
  private LocationService locationService;
  @Ignore
  void selectLocationByCode() {
    CommonResponse response = locationService.selectLocationByCode(511700);
    List<Location> list =( List<Location>) response.getData();
    for(Location location:list){
      System.out.println("--->");
      System.out.println(location.getCityName());
    }
  }
}