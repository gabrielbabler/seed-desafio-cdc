package com.gbabler.challenge_one.dto;

import com.gbabler.challenge_one.domain.Actor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActorRequest(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank @Size(max = 400) String description
) {

    public Actor toModel() {
        return new Actor(this.name, this.email, this.description);
    }
}