package com.example.bestesttheaters.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
public class ApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void listShows() throws Exception {
		mockMvc.perform(get("/api/v1/shows"))
			.andExpect(status().isOk())
			.andExpect(content().json("""
				{
					"shows": []
				}
				"""));
	}

	@Test
	void listBookings() throws Exception {
		mockMvc.perform(get("/api/v1/bookings"))
			.andExpect(status().isOk())
			.andExpect(content().json("""
				{
					"bookings": []
				}
				"""));
	}

	@Test
	void book() throws Exception {
		mockMvc.perform(post("/api/v1/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
						"showId": 1,
						"numberOfTickets": 2
					}"""))
			.andExpect(status().isOk())
			.andExpect(content().json("""
				{
					"bookingId": 1,
					"showId": 1,
					"numberOfTickets": 2
				}"""));
	}

}
