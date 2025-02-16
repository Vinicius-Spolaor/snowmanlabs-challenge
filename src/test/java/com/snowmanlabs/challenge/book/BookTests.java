package com.snowmanlabs.challenge.book;

import com.snowmanlabs.challenge.model.Book;
import com.snowmanlabs.challenge.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BookService bookService;

	@Test
	void shouldCreateBook() throws Exception {
		var bookTitle = "Test Book";

		var book = new Book();
		book.setTitle(bookTitle);
		book.setAuthor("Test Author");
		book.setAvailable(true);

		var saved = bookService.saveBook(book);
		assertNotNull(saved.getId());
		assertEquals(bookTitle, saved.getTitle());
	}


	@Test
	void shouldFindBook() {
		var bookTitle = "Test Book";

		var book = new Book();
		book.setTitle(bookTitle);
		book.setAuthor("Test Author");
		book.setAvailable(true);

		bookService.saveBook(book);

		var bookFound = bookService.searchBooks(bookTitle, "");

		assertFalse(bookFound.isEmpty());
		assertEquals(bookTitle, bookFound.getFirst().getTitle());
	}
}
