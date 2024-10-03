package com.gbabler.challenge_one.dto;

import com.gbabler.challenge_one.domain.Book;

public record BookResponse(
        String id,
        String title
) {
    public static BookResponse from(Book book){
        return new BookResponse(
                book.getId(),
                book.getTitle()
        );
    }
}
