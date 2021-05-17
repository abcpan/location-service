package com.abc.location.aspect;

import com.abc.location.common.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @author abc.pan
 * @version 1.00
 * @time 2020/1/18 20:14
 * @project IntelliJ IDEA
 */
@Configuration
@Aspect
@Slf4j
public class LogProxy<T> {

  private static final  ObjectMapper mapper = new ObjectMapper();
  @Pointcut("within(com.abc.location.controller.*) && @annotation(com.abc.location.annotation.Log)")
  private void point(){}

  /**
   * 打印请求信息
   */
  @Before("point()")
  public void doBeforeLog(JoinPoint joinPoint){
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    String httpMethod = request.getMethod();
    String path = request.getServletPath();
    String ip = request.getRemoteAddr();

    Object[] args = joinPoint.getArgs();
    String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." +joinPoint.getSignature().getName();

    log.info("{} {}, {},{}, from {}",httpMethod, path, args, methodName,ip);
  }


  @AfterReturning(value = "point()", returning = "response")
  public void doAfterLog(CommonResponse response){
    String result = "";
    try{
      result = mapper.writeValueAsString(response);
    }catch (Exception e) {
      log.info(e.getMessage());
    }
    log.info("response: {}", result);
  }

}
