package com.wbxnl.blog.validator.custom;

import com.wbxnl.blog.enums.ContentStateEums;
import com.wbxnl.blog.enums.UserTypeEums;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Author xiaowansheng
 * @Date 2023/8/19 20:39
 */
public class UserTypeValidator implements ConstraintValidator<UserType, String> {

    @Override
    public void initialize(UserType constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null||"".equals(value)) {
            // 不能为空
            return false;
        }
        // Implement your custom validation logic here
        // Return true if valid, false if not
        UserTypeEums userTypeEums = UserTypeEums.getUserTypeEums(value);
        if(userTypeEums!=null){
            return true;
        }
        return false;
    }
}