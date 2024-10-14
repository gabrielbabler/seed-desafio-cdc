package com.gbabler.challenge_one.validation.constraint;

import com.gbabler.challenge_one.validation.annotation.ValidId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidIdValidator implements ConstraintValidator<ValidId, String> {

    @PersistenceContext
    private EntityManager entityManager;

    private String domainAttribute;
    private Class<?> clazz;

    @Override
    public void initialize(ValidId params) {
        domainAttribute = params.fieldName();
        clazz = params.domainClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return true;
        }
        return !entityManager.createQuery("SELECT 1 FROM " + clazz.getName() + " WHERE " + domainAttribute + " = :value")
                .setParameter("value", value)
                .getResultList()
                .isEmpty();
    }
}
