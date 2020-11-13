package com.mort.middleWare.configCenter.server.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DConfig {

    String value() default "";

    String appKey() default "";

}
