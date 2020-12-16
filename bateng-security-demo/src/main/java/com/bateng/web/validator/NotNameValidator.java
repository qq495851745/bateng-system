package com.bateng.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNameValidator implements ConstraintValidator<NotName,String> {
    @Override
    public void initialize(NotName notName) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(s);
        return false;
    }
}
