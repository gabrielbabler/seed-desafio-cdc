package com.gbabler.challenge_one.controller;

import com.gbabler.challenge_one.dto.ActorRequest;
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

/**
 * ICP = 2
 * ActorRequest + 1
 * toModel + 1
 */

@RestController
@RequestMapping("/actors")
public class ActorController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void createActor(@Valid @RequestBody ActorRequest actorRequest) {
        entityManager.persist(actorRequest.toModel());
    }
}
