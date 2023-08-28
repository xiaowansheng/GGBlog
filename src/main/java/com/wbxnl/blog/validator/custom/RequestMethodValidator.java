package com.wbxnl.blog.validator.custom;

import com.wbxnl.blog.enums.ContentStateEums;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Author xiaowansheng
 * @Date 2023/8/19 20:39
 */
public class RequestMethodValidator implements ConstraintValidator<RequestMethod, String> {

    @Override
    public void initialize(RequestMethod constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null||"".equals(value)) {
            return true; // Allow null values, if needed
        }
        if("GET".equalsIgnoreCase(value)){
            return true;
        }

        if("POST".equalsIgnoreCase(value)){
            return true;
        }
        if("PUT".equalsIgnoreCase(value)){
            return true;
        }
        if("DELETE".equalsIgnoreCase(value)){
            return true;
        }
        return false;
    }
}