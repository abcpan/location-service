package com.abc.location.service.impl;

import com.abc.location.common.CommonResponse;
import com.abc.location.dao.LocationMapper;
import com.abc.location.dto.LocationDTO;
import com.abc.location.enums.LocationResultEum;
import com.abc.location.model.Location;
import com.abc.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/20 11:16
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationMapper locationMapper;
    public CommonResponse selectLocationByCode(Integer code){
        Set<Integer> set = this.groupCode(code);
        if(set == null){
            return CommonResponse.createError(LocationResultEum.PARAM_ILLEGAL.getCode(),LocationResultEum.PARAM_ILLEGAL.getMsg());
        }
        List<Location> locations = locationMapper.selectLocationByKeys(set);
        Collections.sort(locations);
        List<LocationDTO> result =  locations.stream().map(location->{
            LocationDTO locationDTO = new LocationDTO();
            Stream<String> stream= Arrays.asList(location.getProvinceName(),location.getCityName(),location.getCountyName()).stream();
            String finalName = stream.filter(name-> !StringUtils.isEmpty(name)).collect(Collectors.joining());
            locationDTO.setName(finalName);
            locationDTO.setCode(location.getCode());
            return locationDTO;
        }).collect(Collectors.toList());
        return CommonResponse.createData(result);
    }
    /**
     * 分类code 码
     * @param locationCode
     * @return
     */
    private Set<Integer> groupCode(Integer locationCode){
        if(locationCode == null){
            return null;
        }
        Set<Integer> set = new HashSet<>();
        Integer provinceCode = locationCode/10000;
        set.add(provinceCode*10000);
        Integer cityCode = locationCode/100;
        set.add(cityCode*100);
        set.add(locationCode);
        return set;
    }

    /**
     * 通过名字查询地区
     * @param name
     * @return
     */
    public CommonResponse selectLocationByName(String name){
        if(name == null){
            return CommonResponse.createError(LocationResultEum.PARAM_ILLEGAL.getCode(),LocationResultEum.PARAM_ILLEGAL.getMsg());
        }
        List<Location> resultList = locationMapper.selectLocationByName(name);
        List<Object> ret = new ArrayList<>();
        for(Location location:resultList){
            Integer code = location.getCode();
            CommonResponse result = this.selectLocationByCode(code);
            ret.add(result.getData());
        }
        return CommonResponse.createData(ret);
    }
}