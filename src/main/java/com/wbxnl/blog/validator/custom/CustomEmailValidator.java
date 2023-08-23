package com.wbxnl.blog.validator.custom;
/**
 * @Author xiaowansheng
 * @Date 2023/8/19 19:22
 */

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Email;

import java.util.regex.Pattern;

public class CustomEmailValidator implements ConstraintValidator<CustomEmail, CharSequence> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$");

    @Override
    public void initialize(CustomEmail annotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Allow null values, if needed
        }
        return EMAIL_PATTERN.matcher(value).matches();
    }
}

