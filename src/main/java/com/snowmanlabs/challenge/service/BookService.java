package com.snowmanlabs.challenge.service;

import ch.qos.logback.core.util.StringUtil;
import com.snowmanlabs.challenge.dto.BookDto;
import com.snowmanlabs.challenge.exception.BusinessException;
import com.snowmanlabs.challenge.model.Book;
import com.snowmanlabs.challenge.repository.BookRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final UserService userService;
    private final RabbitTemplate rabbitTemplate;

    public BookService(BookRepository bookRepository, UserService userService, RabbitTemplate rabbitTemplate) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Page<BookDto> listBooks(Pageable pageable) {
        rabbitTemplate.convertAndSend("books-events", "Listing books");
        return bookRepository.findAll(pageable).map(BookDto::new);
    }

    @Override
    public List<BookDto> searchBooks(String title, String author) {
        List<BookDto> searchResult = new ArrayList<>();

        if (!StringUtil.isNullOrEmpty(title) && !StringUtil.isNullOrEmpty(author)) {
            searchResult = bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author)
                           .stream().map(BookDto::new).toList();
        } else if (!StringUtil.isNullOrEmpty(title)) {
            searchResult = bookRepository.findByTitleContainingIgnoreCase(title)
                           .stream().map(BookDto::new).toList();
        } else if (!StringUtil.isNullOrEmpty(author)) {
            searchResult = bookRepository.findByAuthorContainingIgnoreCase(author)
                           .stream().map(BookDto::new).toList();
        }

        rabbitTemplate.convertAndSend("books-events", "Searched books");
        return searchResult;
    }

    @Override
    public BookDto saveBook(Book book) {
        var saved = bookRepository.save(book);
        rabbitTemplate.convertAndSend("books-events", "Created book: " + saved.getTitle());
        return new BookDto(saved);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
        rabbitTemplate.convertAndSend("books-events", "Deleted book: " + id);
    }

    @Override
    public BookDto updateBook(Long id, Book updatedBook) {
        var updated = bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setAvailable(updatedBook.isAvailable());
            return bookRepository.save(book);
        }).orElseThrow(() -> new BusinessException("Book not found with the id: " + id));

        rabbitTemplate.convertAndSend("books-events", "Updated book: " + updated.getTitle());
        return new BookDto(updated);
    }

    @Override
    public BookDto borrowBook(Long id, String userEmail) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book not found with the id: " + id));

        if (!book.isAvailable()) {
            throw new BusinessException("This book is currently unavailable.");
        }

        var user = userService.findByEmail(userEmail);

        book.setAvailable(false);
        book.setReservedBy(user);
        bookRepository.save(book);

        var message = "Book " + book.getTitle() + " reserved by " + userEmail;
        rabbitTemplate.convertAndSend("books-events", message);

        sendConfirmationEmail(userEmail, book);

        return new BookDto(book);
    }

    public void sendConfirmationEmail(String email, Book book) {
        // #TODO: implementar envio email
    }
}
