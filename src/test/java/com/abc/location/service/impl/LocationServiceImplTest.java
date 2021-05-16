package com.abc.location.service.impl;

import com.abc.location.common.CommonResponse;
import com.abc.location.pojo.Location;
import com.abc.location.service.LocationService;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    CommonResponse response = locationService.selectLocation(511700,null);
    List<Location> list =( List<Location>) response.getData();
    for(Location location:list){
      System.out.println("--->");
    }
  }
}