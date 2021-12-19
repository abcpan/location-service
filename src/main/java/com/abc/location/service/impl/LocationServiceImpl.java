package com.abc.location.service.impl;

import com.abc.location.common.CommonResponse;
import com.abc.location.dao.LocationMapper;
import com.abc.location.utils.EqualUtil;
import com.abc.location.vo.LocationVo;
import com.abc.location.pojo.Location;
import com.abc.location.service.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/20 11:16
 */
@Service
@CacheConfig(cacheNames = {"LOCATION_CACHE"})
public class LocationServiceImpl implements LocationService {
    @Resource
    private LocationMapper locationMapper;

    @Override
    @Cacheable(key="#code + '_' + #name")
    public CommonResponse selectLocation(Integer code,String name) {
        if(code != null){
            return this.selectLocationByCode(code);
        }
        if(!StringUtils.isEmpty(name)){
            return this.selectLocationByName(name);
        }
        return this.selectAll();
    }

    /**
     * 行政区划编码查找
     * @param code
     * @return
     */

    private CommonResponse selectLocationByCode(final Integer code){
       LocationVo ret = this.getCascade(code);
       List<LocationVo> data = ret == null ? new ArrayList<>() : List.of(ret);
       return CommonResponse.createData(data);
    }



    /**
     * 行政区划编码查找
     * @param locationName
     * @return
     */
    private CommonResponse selectLocationByName(String locationName){
        List<Location> locationList = locationMapper.selectLocationByName(locationName);
        List<LocationVo> result  = locationList.stream().map(item-> this.getCascade(item.getCode())).collect(Collectors.toList());
        return CommonResponse.createData(result);
    }

    /**
     * 全部搜索
     * @return
     */
    public CommonResponse selectAll() {
        List<Location> dataSource = locationMapper.selectLocation();
        List<LocationVo> result =  this.findListByParentCode(null, dataSource);
        return CommonResponse.createData(result);
    }

  /**
   * 递归查询子元素
   * @return
   */
    private List<LocationVo> findListByParentCode(Integer parentCode, List<Location> dataSource) {
        return dataSource
            .stream()
            .filter(item -> EqualUtil.isEqual(parentCode, item.getParentCode()))
            .map(location -> {
                LocationVo vo = new LocationVo();
                BeanUtils.copyProperties(location, vo);
                vo.setChildren(this.findListByParentCode(location.getCode(), dataSource));
                return vo;
            })
            .collect(Collectors.toList());
    }


    /**
     * 对结果进行级联
     * @return
     */
    private LocationVo getCascade(Integer locationCode) {
      LinkedList<LocationVo> result = new LinkedList();
      this.collectLocationVo(locationCode, result);
      for(int i = 0; i < result.size() - 1; ++i) {
           result.get(i).getChildren().add(result.get(i+1));
      }
      return result.isEmpty() ? null: result.get(0);
    }

    /**
     * 📱收集链路
     * @param family
     */
    public void collectLocationVo(Integer code, LinkedList<LocationVo> family) {
        Location location = locationMapper.selectLocationByKey(code);
        if(location == null) {
          return;
        }
        LocationVo locationVo = new LocationVo();
        BeanUtils.copyProperties(location, locationVo);

        family.addFirst(locationVo);
        Integer parentCode = location.getParentCode();
        if(parentCode != null) {
            this.collectLocationVo(parentCode, family);
        }
    }
}
