package com.gbabler.challenge_one.controller;

import com.gbabler.challenge_one.domain.Author;
import com.gbabler.challenge_one.dto.AuthorRequest;
import com.gbabler.challenge_one.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthorControllerTest {

    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorRepository authorRepository;

    @Test
    void createActorSuccessfully() {
        AuthorRequest authorRequest = new AuthorRequest("Gabriel", "gabriel@email.com", "description");

        when(authorRepository.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(authorRepository.save(any(Author.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        authorController.createActor(authorRequest);

        verify(authorRepository, times(1)).findByEmail(anyString());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void shouldThrowResponseStatusExceptionWhenCreatingNewActorBecauseEmailExists() {
        AuthorRequest authorRequest = new AuthorRequest("Gabriel", "gabriel@email.com", "description");

        when(authorRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(new Author()));

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> authorController.createActor(authorRequest));
        assertTrue(responseStatusException.getStatusCode().is4xxClientError());
        assertEquals("Email already exists", responseStatusException.getReason());
        assertEquals(HttpStatus.CONFLICT, responseStatusException.getStatusCode());

        verify(authorRepository, times(1)).findByEmail(anyString());
        verify(authorRepository, times(0)).save(any(Author.class));
    }
}
