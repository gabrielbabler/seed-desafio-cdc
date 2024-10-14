package com.gbabler.challenge_one.dto;

import com.gbabler.challenge_one.domain.Country;
import com.gbabler.challenge_one.domain.State;
import com.gbabler.challenge_one.validation.annotation.ValidId;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

public record PaymentRequest(
        @NotBlank(message = "The email field is required")
        String email,

        @NotBlank(message = "The name field is required")
        String name,

        @NotBlank(message = "The lastName field is required")
        String lastName,

        @Size(min = 11, max = 14, message = "The value must be 11 or 14")
        @NotBlank(message = "The document field is required")
        String document,

        @NotBlank(message = "The address field is required")
        String address,

        @NotBlank(message = "The complement field is required")
        String complement,

        @NotBlank(message = "The city field is required")
        String city,

        @NotBlank(message = "The country field is required")
        @ValidId(
                message = "The countryId is not valid",
                fieldName = "id",
                domainClass = Country.class
        )
        String countryId,

        @ValidId(
                message = "The stateId is not valid",
                fieldName = "id",
                domainClass = State.class
        )
        String stateId,

        @NotBlank(message = "The phone field is required")
        String phone,

        @NotBlank(message = "The cep field is required")
        String cep
) {
        public boolean documentIsValid() {
                Assert.hasLength(this.document, "You cannot validate the document if it's empty");
                CPFValidator cpfValidator = new CPFValidator();
                cpfValidator.initialize(null);
                CNPJValidator cnpjValidator = new CNPJValidator();
                cnpjValidator.initialize(null);
                return cpfValidator.isValid(this.document, null) || cnpjValidator.isValid(this.document, null);
        }
}