package com.abc.location.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

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
  @Pointcut("within(com.abc.location.controller.*)")
  private void point(){}

  /**
   * 打印请求信息
   */
  @Before("point() && @annotation(com.abc.location.annotation.Log)")
  public void doLog(JoinPoint joinPoint){
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    String httpMethod = request.getMethod().toUpperCase();
    String path = request.getServletPath();

    Object[] args = joinPoint.getArgs();
    String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." +joinPoint.getSignature().getName();

    log.info("{} {}, {},{}",httpMethod, path,methodName, args);
  }

}
