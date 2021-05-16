package com.abc.location.dao;

import com.abc.location.pojo.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/20 11:15
 */
@Mapper
public interface LocationMapper {
    /**
     * 通过 code 查询
     * @param code
     * @return
     */
    Location selectLocationByKey(@Param("code") Integer code);

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    List<Location> selectLocationByName(@Param("name") String name) ;

    /**
     * 查询所有记录
     * @return
     */
    List<Location> selectLocation();
}
