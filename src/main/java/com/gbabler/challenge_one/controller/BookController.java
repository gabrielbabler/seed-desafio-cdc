package com.gbabler.challenge_one.controller;

import com.gbabler.challenge_one.domain.Book;
import com.gbabler.challenge_one.dto.BookByIdResponse;
import com.gbabler.challenge_one.dto.BookRequest;
import com.gbabler.challenge_one.dto.BookResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * ICP = 3
 * bookReq = 1
 * toModel = 1
 * from = 1
 */

@RestController
@RequestMapping("/books")
public class BookController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void createBook(@Valid @RequestBody BookRequest bookRequest) {
        entityManager.persist(bookRequest.toModel(entityManager));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponse> getAllBooks() {
        return entityManager.createQuery("select b from Book b", Book.class)
                .getResultList()
                .stream()
                .map(BookResponse::from)
                .toList();
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookByIdResponse getBookById(@PathVariable("bookId") String bookId) {
        Book book = entityManager.find(Book.class, bookId);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return BookByIdResponse.from(book);
    }
}
