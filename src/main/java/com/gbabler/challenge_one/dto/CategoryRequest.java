package com.gbabler.challenge_one.dto;

import com.gbabler.challenge_one.domain.Category;
import com.gbabler.challenge_one.validation.annotation.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Field name cannot be blank")
        @UniqueValue(message = "This name already exists", fieldName = "name", domainClass = Category.class)
        String name
) {
    public Category toModel() {
        return new Category(this.name);
    }
}
