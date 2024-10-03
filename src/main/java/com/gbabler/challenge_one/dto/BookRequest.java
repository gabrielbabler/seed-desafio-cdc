package com.gbabler.challenge_one.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbabler.challenge_one.domain.Author;
import com.gbabler.challenge_one.domain.Book;
import com.gbabler.challenge_one.domain.Category;
import com.gbabler.challenge_one.validation.annotation.UniqueValue;
import com.gbabler.challenge_one.validation.annotation.ValidId;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookRequest(
        @NotBlank @UniqueValue(
                message = "This title already exists",
                fieldName = "title",
                domainClass = Book.class
        ) String title,
        @NotBlank(message = "Field abstract cannot be blank") @JsonProperty(value = "abstract") String abztract,
        String summary,
        @NotNull Float price,
        @NotNull Integer numberOfPages,
        @NotBlank @UniqueValue(
                message = "This ISBN already exists",
                fieldName = "isbn",
                domainClass = Book.class
        ) String isbn,
        @Future @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) LocalDate releaseDate,
        @NotBlank @ValidId(
                message = "The categoryId is invalid",
                fieldName = "id",
                domainClass = Category.class
        ) String categoryId,
        @NotBlank @ValidId(
                message = "The authorId is invalid",
                fieldName = "id",
                domainClass = Author.class
        ) String authorId
) {
        public Book toModel(EntityManager entityManager) {
                Author author = entityManager.find(Author.class, authorId);
                Category category = entityManager.find(Category.class, categoryId);

                return new Book(
                        this.title,
                        this.abztract,
                        this.summary,
                        this.price,
                        this.numberOfPages,
                        this.isbn,
                        this.releaseDate,
                        category,
                        author
                );
        }
}