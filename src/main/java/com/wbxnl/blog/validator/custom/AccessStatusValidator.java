package com.wbxnl.blog.validator.custom;

import com.wbxnl.blog.enums.ContentStateEums;
import com.wbxnl.blog.enums.TopicTypeEums;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Author xiaowansheng
 * @Date 2023/8/19 20:39
 */
public class AccessStatusValidator implements ConstraintValidator<TopicType, String> {

    @Override
    public void initialize(TopicType constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Allow null values, if needed
        }
        // Implement your custom validation logic here
        // Return true if valid, false if not
        ContentStateEums contentStatusEums = ContentStateEums.getContentStatusEums(value);
        if(contentStatusEums!=null){
            return true;
        }
        return false;
    }
}