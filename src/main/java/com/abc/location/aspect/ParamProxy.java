package com.abc.location.aspect;

import com.abc.location.exceptions.FieldException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

/**
 * @author abc
 * @version V1.0
 * @date 2021/6/5 12:28 上午
 */
@Configuration
@Aspect
@Slf4j
public class ParamProxy {

  @Before("within(com.abc.location.controller.*) && args(..,bindingResult)")
  public void handleParamError(BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      throw new FieldException("参数错误!");
    }
  }
}
