package com.abc.location.exceptions;

import com.abc.location.common.CommonResponse;
import com.abc.location.consts.ResultMsg;
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

  @ExceptionHandler(FieldException.class)
  public CommonResponse handleFieldError(HttpServletRequest request,Exception e){
    e.printStackTrace();
    return CommonResponse.createError(CommonResponse.ERROR_CODE, ResultMsg.PARAM_ERROR_MSG);
  }

  @ExceptionHandler(Exception.class)
  public CommonResponse handleAll(HttpServletRequest request,Exception e){
    e.printStackTrace();
    return CommonResponse.createError(CommonResponse.ERROR_CODE, ResultMsg.SERVER_ERROR_MSG);
  }
}
