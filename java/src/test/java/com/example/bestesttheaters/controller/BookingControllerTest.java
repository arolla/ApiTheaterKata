/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bestesttheaters.controller;

import com.example.bestesttheaters.data.Booking;
import com.example.bestesttheaters.data.InMemoryRepository;
import com.example.bestesttheaters.data.Show;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
@DisabledInNativeImage
@DisabledInAotMode
class BookingControllerTest {

	private final LocalDateTime TOMORROW = LocalDateTime.parse("2021-07-01T00:00:00");
	private final Show MIRACULOUS = Show.createShow(1, TOMORROW, "Miraculous");

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InMemoryRepository showRepository;

	@BeforeEach
	void setup() {
		List<Show> shows = List.of(
			MIRACULOUS,
			Show.createShow(2, TOMORROW, "Les As de la jungle 2"),
			Show.createShow(3, TOMORROW, "Anatomie d'une chute"),
			Show.createShow(4, TOMORROW, "Tempête"),
			Show.createShow(5, TOMORROW, "Passages"),
			Show.createShow(6, TOMORROW, "Mon chat et moi"),
			Show.createShow(7, TOMORROW, "Les Choses simples"),
			Show.createShow(8, TOMORROW, "Le Bleu du caftan"),
			Show.createShow(9, TOMORROW, "Mon crime"));

		given(showRepository.findAll()).willReturn(shows);
	}

	@Test
	void testProcessNewBookingForm() throws Exception {
		mockMvc.perform(post("/shows/1/booking/new").param("numberOfTickets", "3"))
			.andExpect(status().is3xxRedirection());
		Booking booking = Booking.createBooking(1, MIRACULOUS, 3);
		verify(showRepository).saveBooking(booking);
	}

}
