package com.gbabler.challenge_one.dto;

import com.gbabler.challenge_one.domain.Author;
import com.gbabler.challenge_one.validation.annotation.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorRequest(
        @NotBlank(message = "Field name cannot be blank")
        @UniqueValue(message = "This name already exists", fieldName = "name", domainClass = Author.class)
        String name,

        @NotBlank(message = "Field email cannot be blank")
        @Email
        String email,

        @NotBlank(message = "Field description cannot be blank")
        @Size(max = 400)
        String description
) {

    public Author toModel() {
        return new Author(this.name, this.email, this.description);
    }
}