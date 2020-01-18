package com.abc.location.dao;

import com.abc.location.model.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/20 11:15
 */
@Mapper
public interface LocationMapper {
    List<Location> selectLocationByKeys(@Param("codes") Set<Integer> codes);
    List<Location> selectLocationByName(@Param("name") String name);
}
