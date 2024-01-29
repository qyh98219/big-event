package com.github.validation;

import com.github.annotation.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @ClassName StateValidation
 * @Description 自定义文章state校验
 * @Author qyh
 * @Date 2024/1/29 19:48
 * @Version 1.0
 **/
public class StateValidation implements ConstraintValidator<State, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return false;
        }

        return value.equals("已发布") || value.equals("草稿");
    }
}
