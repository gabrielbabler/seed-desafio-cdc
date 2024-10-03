package com.gbabler.challenge_one.controller;

import com.gbabler.challenge_one.dto.AuthorRequest;
import com.gbabler.challenge_one.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * ICP = 3
 * ActorRepository + 1
 * ActorRequest + 1
 * toModel + 1
 */

@RestController
@RequestMapping("/actors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void createActor(@Valid @RequestBody AuthorRequest authorRequest) {
        authorRepository.save(authorRequest.toModel());
    }
}
