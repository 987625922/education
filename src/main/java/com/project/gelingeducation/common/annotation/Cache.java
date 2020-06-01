package com.project.gelingeducation.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    //过期时间
    long time() default 30*60;
}
