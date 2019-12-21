package com.abc.location.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author abcpan
 * @version 1.0
 * @date 2019/12/20 11:21
 */
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> implements Serializable {
    private static final Integer OK_CODE = 0;
    private int code;
    private String msg;
    private T data;
    public CommonResponse(int code ,String msg){
        this.code = code;
        this.msg = msg;
    }
    public CommonResponse(int code,T data){
        this.code = code;
        this.data = data;
    }
    public CommonResponse(int code){
        this.code = code;
    }
    @JsonIgnore
    public boolean isOk(){
        return this.code == OK_CODE;
    }

    public static  CommonResponse createOk(){
        return new CommonResponse(OK_CODE);
    }
    public static <T> CommonResponse<T> createData(T data){
        return new CommonResponse<>(OK_CODE,data);
    }
    public static  CommonResponse createError(int code,String msg){
        return new CommonResponse(code,msg);
    }
}
