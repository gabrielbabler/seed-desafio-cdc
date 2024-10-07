package com.gbabler.challenge_one.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbabler.challenge_one.domain.Author;
import com.gbabler.challenge_one.domain.Book;

import java.time.LocalDate;

public record BookByIdResponse(
        String title,
        String summary,
        @JsonProperty("abstract") String abztract,
        Float price,
        Integer numberOfPages,
        String isbn,
        LocalDate releaseDate,
        Author author
) {
    public static BookByIdResponse from(Book book) {
        return new BookByIdResponse(
                book.getTitle(),
                book.getSummary(),
                book.getAbztract(),
                book.getPrice(),
                book.getNumberOfPages(),
                book.getIsbn(),
                book.getReleaseDate(),
                book.getAuthor()
        );
    }
}
