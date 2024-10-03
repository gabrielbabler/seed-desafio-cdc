package com.gbabler.challenge_one.dto;

import com.gbabler.challenge_one.domain.Author;
import com.gbabler.challenge_one.validation.annotation.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorRequest(
        @NotBlank @UniqueValue(message = "This name already exists", fieldName = "name", domainClass = Author.class) String name,
        @NotBlank @Email String email,
        @NotBlank @Size(max = 400) String description
) {

    public Author toModel() {
        return new Author(this.name, this.email, this.description);
    }
}