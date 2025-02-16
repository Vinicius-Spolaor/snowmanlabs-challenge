package com.snowmanlabs.challenge.model;

import com.snowmanlabs.challenge.dto.BookDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @ManyToOne
    @JoinColumn(name = "reserved_by_user_id")
    private User reservedBy;

    private boolean available = true;

    public Book(BookDto bookDto) {
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
        this.available = bookDto.isAvailable();

        if (Objects.nonNull(bookDto.getReservedByUser())) {
            this.reservedBy = new User(bookDto.getReservedByUser());
        }
    }
}
