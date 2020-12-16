package com.bateng.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNameValidator.class)
public @interface NotName {
    String message() default "不能为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
