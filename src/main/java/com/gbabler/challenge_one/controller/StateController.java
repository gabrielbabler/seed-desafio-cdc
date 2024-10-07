package com.gbabler.challenge_one.controller;

import com.gbabler.challenge_one.dto.StateRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/states")
public class StateController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void createCountry(@Valid @RequestBody StateRequest stateRequest) {
        entityManager.persist(stateRequest.toModel(entityManager));
    }
}
