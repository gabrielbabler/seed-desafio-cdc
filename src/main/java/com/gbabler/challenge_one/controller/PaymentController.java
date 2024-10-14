package com.gbabler.challenge_one.controller;

import com.gbabler.challenge_one.dto.PaymentRequest;
import com.gbabler.challenge_one.validation.DocumentValidator;
import com.gbabler.challenge_one.validation.StateBelongsToCountryValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PersistenceContext
    private EntityManager entityManager;

    private final StateBelongsToCountryValidator stateBelongsToCountryValidator;

    public PaymentController(StateBelongsToCountryValidator stateBelongsToCountryValidator) {
        this.stateBelongsToCountryValidator = stateBelongsToCountryValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new DocumentValidator(), stateBelongsToCountryValidator);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void pay(@Valid @RequestBody PaymentRequest paymentRequest){

    }
}
