package com.snowmanlabs.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snowmanlabs.challenge.model.Book;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private UserDto reservedByUser;
    private boolean available = true;

    public BookDto(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.id = book.getId();
        this.available = book.isAvailable();

        if (Objects.nonNull(book.getReservedBy())) {
            this.reservedByUser = new UserDto(book.getReservedBy());
        }
    }
}
