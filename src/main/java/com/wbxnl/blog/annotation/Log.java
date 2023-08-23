package com.wbxnl.blog.annotation;

import java.lang.annotation.*;


/**
 * 自定义日志注解
 * @author wansheng
 * @createDate 2022/8/27 0:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

//    /**
//     * 操作模块
//     * @return
//     */
//    String modul() default "";

    /**
     * 操作类型
     * @return
     */
    String type() default "";

    /**
     * 操作说明
     * @return
     */
    String desc() default "";

}

