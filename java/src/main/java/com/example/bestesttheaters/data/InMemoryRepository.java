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
package com.example.bestesttheaters.data;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryRepository {

	private final List<Show> shows;
	private final List<Booking> bookings;

	public InMemoryRepository() {
		LocalDateTime TOMORROW = LocalDateTime.now().plusDays(1);
		shows = List.of(
			Show.createShow(1, TOMORROW, "Miraculous"),
			Show.createShow(2, TOMORROW, "Les As de la jungle 2"),
			Show.createShow(3, TOMORROW, "Anatomie d'une chute"),
			Show.createShow(4, TOMORROW, "Tempête"),
			Show.createShow(5, TOMORROW, "Passages"),
			Show.createShow(6, TOMORROW, "Mon chat et moi"),
			Show.createShow(7, TOMORROW, "Les Choses simples"),
			Show.createShow(8, TOMORROW, "Le Bleu du caftan"),
			Show.createShow(9, TOMORROW, "Mon crime"));
		bookings = new ArrayList<>();
	}

	public List<Show> findAll() {
		return shows;
	}

	public List<Booking> findAllBookings() {
		return bookings;
	}

	public void saveBooking(Booking show) {
		bookings.add(show);
	}
}
