package com.gbabler.challenge_one.validation;

import com.gbabler.challenge_one.dto.PaymentRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DocumentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PaymentRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }
        PaymentRequest request = (PaymentRequest) target;
        if(!request.documentIsValid()) {
            errors.rejectValue("document", null, "Document is invalid");
        }
    }
}
