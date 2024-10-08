package com.gbabler.challenge_one.validation.annotation;

import com.gbabler.challenge_one.validation.constraint.UniqueFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UniqueFieldValidator.class)
@Target( { ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {
    String message() default "Field must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> domainClass();
}
