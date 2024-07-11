package com.example.validation.annotation;

import com.example.validation.validator.YearMonthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {YearMonthValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface YearMonth {

    String message() default "Client가 요청한 내용이 yyyyMM 형식이 아닙니다";

    String regexp() default "^(19|20)\\d\\d(0[1-9]|1[0-2])$";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
