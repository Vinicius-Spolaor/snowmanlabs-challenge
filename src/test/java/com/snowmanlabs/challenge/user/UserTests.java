package com.snowmanlabs.challenge.user;

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
class UserTests {
	private static final String USER_URL = "/v1/users";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldCreateUser() throws Exception {
		var userJson = "{\"name\":\"Test Name\",\"email\":\"test@email.com\",\"password\":\"test\"}";

		this.mockMvc.perform(MockMvcRequestBuilders.post(USER_URL + "/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Test Name"));
	}
}
