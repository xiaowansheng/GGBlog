package com.wbxnl.blog.validator.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @Author xiaowansheng
 * @Date 2023/8/19 19:26
 */
@Documented
@Constraint(validatedBy = CustomEmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomEmail {

    String message() default "邮箱不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}