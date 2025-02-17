package com.snowmanlabs.challenge.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowmanlabs.challenge.model.Book;
import com.snowmanlabs.challenge.model.User;
import com.snowmanlabs.challenge.repository.BookRepository;
import com.snowmanlabs.challenge.repository.UserRepository;
import com.snowmanlabs.challenge.service.BookService;
import com.snowmanlabs.challenge.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private BookRepository bookRepository;

	@Mock
	private BookService bookService;

	@Test
	void shouldListBooksWithPagination() throws Exception {
		when(bookRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
		mockMvc.perform(get("/v1/books?page=0&size=5"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").isArray());
	}

}
