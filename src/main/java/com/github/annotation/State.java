package com.github.annotation;

import com.github.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StateValidation.class)
public @interface State {

    String message() default "文章状态只能是: 已发布或者草稿";

    Class[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
