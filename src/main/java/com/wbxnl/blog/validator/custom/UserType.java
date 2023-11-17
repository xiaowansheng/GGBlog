package com.wbxnl.blog.validator.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @Author xiaowansheng
 * @Date 2023/8/19 20:38
 */
@Documented
@Constraint(validatedBy = UserTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserType {

    String message() default "用户类型不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}