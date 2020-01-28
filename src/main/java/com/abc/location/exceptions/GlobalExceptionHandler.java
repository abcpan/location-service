package com.abc.location.exceptions;

import com.abc.location.common.CommonResponse;
import com.abc.location.enums.LocationResultEum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author abc.pan
 * @version 1.00
 * @time 2020/1/28 13:57
 * @project IntelliJ IDEA
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public CommonResponse handleAll(HttpServletRequest request,Exception e){
    String path = request.getServletPath();
    log.warn("path==>{} occur a exception==>{}",path,e.getMessage());
    return CommonResponse.createError(LocationResultEum.SERVER_ERROR.getCode(),LocationResultEum.SERVER_ERROR.getMsg());
  }
}
