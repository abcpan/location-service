package com.abc.location.service.impl;

import com.abc.location.common.CommonResponse;
import com.abc.location.dao.LocationMapper;
import com.abc.location.dto.LocationDTO;
import com.abc.location.enums.LocationResultEum;
import com.abc.location.model.Location;
import com.abc.location.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = {"LOCATION_CACHE"})
@Slf4j
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationMapper locationMapper;

    @Cacheable(key="#code + '_' + #name")
    public CommonResponse selectLocation(Integer code,String name){

        if(code !=null){
            log.info("query from database by code={}",code);
            return this.selectLocationByCode(code);
        }
        if(!StringUtils.isEmpty(name)){
            log.info("query from database by name={}",name);
            return this.selectLocationByName(name);
        }
        return CommonResponse.createError(LocationResultEum.PARAM_ILLEGAL.getCode(),LocationResultEum.PARAM_ILLEGAL.getMsg());
    }
    /**
     * 行政区划编码查找
     * @param code
     * @return
     */

    private CommonResponse selectLocationByCode(final Integer code){
        Set<Integer> set = this.groupCode(code);
        List<Location> locations = locationMapper.selectLocationByKeys(set);
        List<LocationDTO> result =  locations.stream()
            .sorted()
            .map(location->{
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
     * @param code
     * @return
     */
    private Set<Integer> groupCode(final Integer code){
        Set<Integer> set = new HashSet<>();
        Integer provinceCode = code/10000;
        set.add(provinceCode*10000);
        Integer cityCode = code/100;
        set.add(cityCode*100);
        set.add(code);
        return set;
    }

    /**
     * 通过名字查询地区
     * @param name
     * @return
     */
    private CommonResponse selectLocationByName(String name){
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
