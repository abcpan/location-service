package com.abc.location.pojo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/20 11:04
 */
@Data
@NoArgsConstructor
public class Location{
    private Integer code;
    private String name;
    private Integer type;
    private Integer parentCode;
}
