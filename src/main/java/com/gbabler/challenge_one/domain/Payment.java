package com.gbabler.challenge_one.domain;

import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.Assert;

public class Payment {
        @Email @NotBlank private String email;
        @NotBlank private String name;
        @NotBlank private String lastName;
        @NotBlank private String document;
        @NotBlank private String address;
        @NotBlank private String complement;
        @ManyToOne
        @NotNull private Country country;
        @ManyToOne
        private State state;
        @NotBlank private String phone;
        @NotBlank private String cep;

    public Payment(String email, String name, String lastName, String document, String address, String complement, Country country, String phone, String cep) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.country = country;
        this.phone = phone;
        this.cep = cep;
    }

    public @Email @NotBlank String getEmail() {
        return email;
    }

    public @NotBlank String getName() {
        return name;
    }

    public @NotBlank String getLastName() {
        return lastName;
    }

    public @NotBlank String getDocument() {
        return document;
    }

    public @NotBlank String getAddress() {
        return address;
    }

    public @NotBlank String getComplement() {
        return complement;
    }

    public @NotNull Country getCountry() {
        return country;
    }

    public State getState() {
        return state;
    }

    public @NotBlank String getPhone() {
        return phone;
    }

    public @NotBlank String getCep() {
        return cep;
    }

    public void setState(@NotNull @Valid State state) {
        Assert.notNull(country, "Country must not be null");
        Assert.isTrue(state.belongsTo(country), "This state does not belong to this country");
        this.state = state;
    }
}
