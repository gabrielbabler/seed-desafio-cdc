package com.gbabler.challenge_one.validation.constraint;

import com.gbabler.challenge_one.validation.annotation.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueFieldValidator implements ConstraintValidator<UniqueValue, String> {

    @PersistenceContext
    private EntityManager entityManager;

    private String domainAttribute;
    private Class<?> clazz;

    @Override
    public void initialize(UniqueValue params) {
        domainAttribute = params.fieldName();
        clazz = params.domainClass();
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return entityManager.createQuery("SELECT 1 FROM " + clazz.getName() + " WHERE " + domainAttribute + " = :field")
                .setParameter("field", field)
                .getResultList()
                .isEmpty();
    }
}
