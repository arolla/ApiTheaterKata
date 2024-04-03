package com.example.bestesttheaters.api;

import com.example.bestesttheaters.data.BookingService;
import com.example.bestesttheaters.data.InMemoryRepository;
import com.example.bestesttheaters.data.Show;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
@Import(BookingService.class)
public class ApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InMemoryRepository repository;

	@BeforeEach
	void setUp() {
		List<Show> show = List.of(
			Show.createShowMediumCapacity(1,
				LocalDateTime.parse("2021-12-01T20:00:00"),
				"The Matrix")
		);
		when(repository.findAll()).thenReturn(show);
	}

	@Test
	void singleShow() throws Exception {
		mockMvc.perform(get("/api/v1/shows/1"))
			.andExpect(status().isOk())
			.andExpect(content().json("""
				{
					"id":1,
					"title":"The Matrix",
					"date":"1 déc. 2021, 20:00:00"
				}"""));
	}

	@Test
	void showNotFound() throws Exception {
		mockMvc.perform(get("/api/v1/shows/42"))
			.andExpect(status().isNotFound());
	}

	@Test
	void listShows() throws Exception {
		mockMvc.perform(get("/api/v1/shows"))
			.andExpect(status().isOk())
			.andExpect(content().json("""
				{
					"shows": [
						{
							"id":1,
							"title":"The Matrix",
							"date":"1 déc. 2021, 20:00:00"
						}
					],
					"_links": {
						"self": {
							"href":"http://localhost/api/v1/shows"
						}
					}
				}"""));
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
