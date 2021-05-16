package com.abc.location.annotation;

import java.lang.annotation.*;

/**
 * @author abc
 * @version V1.0
 * @date 2021/5/16 6:41 下午
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Log {
  String description() default "";
}
