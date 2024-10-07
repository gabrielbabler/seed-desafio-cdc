package com.gbabler.challenge_one.dto;

import com.gbabler.challenge_one.domain.Country;
import com.gbabler.challenge_one.validation.annotation.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public record CountryRequest(
        @NotBlank(message = "Field name cannot be blank")
        @UniqueValue(
                fieldName = "name",
                domainClass = Country.class
        )
        String name
) {
    public Country toModel() {
        return new Country(
                this.name
        );
    }
}