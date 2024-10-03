package com.gbabler.challenge_one.controller;

import com.gbabler.challenge_one.domain.Book;
import com.gbabler.challenge_one.dto.BookRequest;
import com.gbabler.challenge_one.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ICP = 4
 * bookRepo = 1
 * bookReq = 1
 * toModel = 1
 * Book = 1
 */

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final EntityManager entityManager;

    public BookController(BookRepository bookRepository, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void createBook(@Valid @RequestBody BookRequest bookRequest) {
        entityManager.persist(bookRequest.toModel(entityManager));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
