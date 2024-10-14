package com.gbabler.challenge_one.validation;

import com.gbabler.challenge_one.domain.Country;
import com.gbabler.challenge_one.domain.State;
import com.gbabler.challenge_one.dto.PaymentRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StateBelongsToCountryValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

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
        Country country = entityManager.find(Country.class, request.countryId());
        State state = entityManager.find(State.class, request.stateId());

        if(!state.belongsTo(country)) {
            errors.rejectValue("stateId", null, "State does not belong to this country");
        }
    }
}
