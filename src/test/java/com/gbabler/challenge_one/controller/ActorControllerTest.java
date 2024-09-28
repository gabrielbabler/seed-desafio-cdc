package com.gbabler.challenge_one.controller;

import com.gbabler.challenge_one.domain.Actor;
import com.gbabler.challenge_one.dto.ActorRequest;
import com.gbabler.challenge_one.repository.ActorRepository;
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
public class ActorControllerTest {

    @InjectMocks
    private ActorController actorController;

    @Mock
    private ActorRepository actorRepository;

    @Test
    void createActorSuccessfully() {
        ActorRequest actorRequest = new ActorRequest("Gabriel", "gabriel@email.com", "description");

        when(actorRepository.findByEmail(anyString()))
                .thenReturn(Optional.empty());
        when(actorRepository.save(any(Actor.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        actorController.createActor(actorRequest);

        verify(actorRepository, times(1)).findByEmail(anyString());
        verify(actorRepository, times(1)).save(any(Actor.class));
    }

    @Test
    void shouldThrowResponseStatusExceptionWhenCreatingNewActorBecauseEmailExists() {
        ActorRequest actorRequest = new ActorRequest("Gabriel", "gabriel@email.com", "description");

        when(actorRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(new Actor()));

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> actorController.createActor(actorRequest));
        assertTrue(responseStatusException.getStatusCode().is4xxClientError());
        assertEquals("Email already exists", responseStatusException.getReason());
        assertEquals(HttpStatus.CONFLICT, responseStatusException.getStatusCode());

        verify(actorRepository, times(1)).findByEmail(anyString());
        verify(actorRepository, times(0)).save(any(Actor.class));
    }
}
