package com.abc.location.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
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
public class AspectHandler<T> {
  @Pointcut("within(com.abc.location.controller.*)")
  private void point(){}

  /**
   * 打印请求信息
   */
  @Before("point() && args(code,name)")
  public void handleLog(Integer code,String name){
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    String path = request.getServletPath();
    log.info("request from ===>{},the param is code===>{},name===>{}",path,code,name);
  }
}
