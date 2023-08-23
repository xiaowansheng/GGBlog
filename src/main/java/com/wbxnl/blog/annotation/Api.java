package com.wbxnl.blog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器模块名
 * @author wansheng
 * @createDate 2023/7/20 17:44
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Api {
    /**
     * 模块名
     * @return
     */
    String value() default "";
    String[] description() default "";
}
