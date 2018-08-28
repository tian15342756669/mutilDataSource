package com.hispeed.config;

import java.lang.annotation.*;

/**
 * Created by dengtg on 2018-8-28.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface DataSourceAnnotation {
}
