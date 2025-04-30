package com.gbabler.challenge_one.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ShoppingCartRequest(
    @NotNull(message = "Field total cannot be null") @Min(1) BigDecimal total,
    @NotEmpty(message = "Must be at least one item") List<@Valid ShoppingCartItem> items
) {}