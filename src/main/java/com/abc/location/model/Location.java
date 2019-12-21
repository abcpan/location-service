package com.abc.location.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/20 11:04
 */
@Data
@NoArgsConstructor
public class Location implements Comparable{
    private Integer code;
    private String countyName;
    private Integer cityCode;
    private String cityName;
    private Integer provinceCode;
    private String provinceName;
    private Integer type;

    @Override
    public int compareTo(@NonNull Object o) {
        Location other = (Location) o;
        return this.type.compareTo(other.type);
    }
}
