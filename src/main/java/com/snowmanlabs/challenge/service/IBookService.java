package com.snowmanlabs.challenge.service;

import com.snowmanlabs.challenge.dto.BookDto;
import com.snowmanlabs.challenge.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {
    Page<BookDto> listBooks(Pageable pageable);
    List<BookDto> searchBooks(String title, String author);
    BookDto saveBook(Book book);
    void deleteBook(Long id);
    BookDto updateBook(Long id, Book updatedBook);
    BookDto borrowBook(Long id, String userEmail);
}
