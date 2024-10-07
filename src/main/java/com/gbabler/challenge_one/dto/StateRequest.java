package com.gbabler.challenge_one.dto;

import com.gbabler.challenge_one.domain.Author;
import com.gbabler.challenge_one.domain.Country;
import com.gbabler.challenge_one.domain.State;
import com.gbabler.challenge_one.validation.annotation.UniqueValue;
import com.gbabler.challenge_one.validation.annotation.ValidId;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;

public record StateRequest(
        @NotBlank(message = "Field name cannot be blank")
        @UniqueValue(
                fieldName = "name",
                domainClass = State.class
        )
        String name,

        @NotBlank(message = "Field countryId cannot be blank")
        @ValidId(
                message = "The country is invalid",
                fieldName = "id",
                domainClass = Country.class
        )
        String countryId
) {
    public State toModel(EntityManager entityManager) {
        Country country = entityManager.find(Country.class, this.countryId);
        return new State(
                name,
                country
        );
    }
}