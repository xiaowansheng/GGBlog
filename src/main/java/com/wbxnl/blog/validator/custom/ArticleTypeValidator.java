package com.wbxnl.blog.validator.custom;

import com.wbxnl.blog.enums.ArticleTypeEums;
import com.wbxnl.blog.enums.UserTypeEums;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Author xiaowansheng
 * @Date 2023/8/19 20:39
 */
public class ArticleTypeValidator implements ConstraintValidator<TopicType, String> {

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
        ArticleTypeEums typeEums = ArticleTypeEums.getArticleTypeEums(value);
        if(typeEums!=null){
            return true;
        }
        return false;
    }
}