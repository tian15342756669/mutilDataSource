package com.hispeed.config;

import java.lang.annotation.*;

/**
 * Created by dengtg on 2018-8-28.
 */
@Retention(RetentionPolicy.RUNTIME)//定义该Annotation被保留的时间长短
@Target({ElementType.METHOD, ElementType.TYPE})//用于说明注解所修饰的对象范围
@Documented//用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化
public @interface DataSourceAnnotation {
}
