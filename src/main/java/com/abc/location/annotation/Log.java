package com.abc.location.annotation;

import java.lang.annotation.*;

/**
 * @author abc
 * @version V1.0
 * @date 2021/5/16 6:41 下午
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
  String value() default "";
}
