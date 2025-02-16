package com.snowmanlabs.challenge.controller;

import com.snowmanlabs.challenge.dto.BookDto;
import com.snowmanlabs.challenge.model.Book;
import com.snowmanlabs.challenge.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Page<BookDto> listBooks(Pageable pageable) {
        return bookService.listBooks(pageable);
    }

    @GetMapping("/search")
    public List<BookDto> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        return bookService.searchBooks(title, author);
    }

    @PostMapping
    public BookDto createBook(@RequestBody @Valid BookDto bookDto) {
        return bookService.saveBook(new Book(bookDto));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody @Valid BookDto bookDto) {
        return bookService.updateBook(id, new Book(bookDto));
    }

    @PatchMapping("/{id}/borrow")
    public BookDto borrowBook(@PathVariable Long id, @RequestParam String email) {
        return bookService.borrowBook(id, email);
    }
}
