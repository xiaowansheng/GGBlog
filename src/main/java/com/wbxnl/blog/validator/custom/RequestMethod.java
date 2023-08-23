package com.wbxnl.blog.validator.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @Author xiaowansheng
 * @Date 2023/8/19 20:38
 */
@Documented
@Constraint(validatedBy = RequestMethodValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMethod {

    String message() default "请求方法不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}