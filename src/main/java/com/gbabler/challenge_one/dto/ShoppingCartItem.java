package com.gbabler.challenge_one.dto;

import com.gbabler.challenge_one.domain.Book;
import com.gbabler.challenge_one.validation.annotation.ValidId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ShoppingCartItem(
        @NotBlank
        @ValidId(
                message = "The bookId is invalid",
                fieldName = "id",
                domainClass = Book.class
        ) String bookId,

        @NotNull(message = "Field quantity is required")
        @Min(1)
        Integer quantity
) {}