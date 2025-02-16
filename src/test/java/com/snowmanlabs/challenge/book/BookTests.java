package com.snowmanlabs.challenge.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookTests {
	private static final String BOOK_URL = "/v1/books";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldCreateBook() throws Exception {
		var jsonBook = "{\"title\":\"Test Book\",\"author\":\"Test Author\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(BOOK_URL)
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonBook))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Test Book"));
	}

	@Test
	void shouldListBooks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(BOOK_URL)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void shouldSearchBook() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(BOOK_URL + "/search?title=book&author=author")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
