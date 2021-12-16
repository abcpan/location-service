package com.abc.location.utils;

import com.abc.location.annotation.Log;
import org.aspectj.lang.JoinPoint;
import java.lang.reflect.Method;

/**
 * @author abc
 * @version V1.0
 * @date 2021/5/17 9:45 下午
 */
public class AnnotationUtil {
  /**
   * 获取日志注解的描述信息
   * @param joinPoint
   * @return
   */
  public static<T extends Log> String getLogDescriptionFromJoinPoint(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    Object[] arguments = joinPoint.getArgs();
    Method[] methods = joinPoint.getTarget().getClass().getMethods();
    StringBuilder sb = new StringBuilder("");
    for (Method method : methods) {
      if (
          method.getName().equals(methodName)
          && arguments.length == method.getParameterCount()
          && method.isAnnotationPresent(Log.class)
      ) {
        sb.append(method.getAnnotation(Log.class).value());
        break;
      }
    }
    return sb.toString();
  }

}
