package com.wbxnl.blog.validator.custom;

import com.wbxnl.blog.enums.ArticleTypeEums;
import com.wbxnl.blog.enums.PictureTypeEums;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Author xiaowansheng
 * @Date 2023/8/19 20:39
 */
public class PictureTypeValidator implements ConstraintValidator<TopicType, String> {

    @Override
    public void initialize(TopicType constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // Implement your custom validation logic here
        // Return true if valid, false if not
        PictureTypeEums typeEums = PictureTypeEums.getPictureTypeEums(value);
        if(typeEums!=null){
            return true;
        }
        return false;
    }
}