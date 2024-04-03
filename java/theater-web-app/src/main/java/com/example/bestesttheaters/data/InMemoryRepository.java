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

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InMemoryRepository {

	private final static Logger LOG = LoggerFactory.getLogger(InMemoryRepository.class);

	private final List<Show> shows = new ArrayList<>();
	private final List<Booking> bookings;
	private final String showsFile;
	private final String bookingsFile;

	public InMemoryRepository(@Value("${shows.file}") String showsFile, @Value("${bookings.file}") String bookingsFile) {
		this.showsFile = showsFile;
		this.bookingsFile = bookingsFile;
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

	@PostConstruct
	public void init() throws IOException {
		loadShows(Path.of(showsFile));
		loadBookings(Path.of(bookingsFile));
	}

	private void loadShows(Path jsonFile) throws IOException {
		shows.clear();
		Files.readAllLines(jsonFile, StandardCharsets.UTF_8).forEach(line -> {
			String[] columns = line.split(";");
			Show show = Show.createShow(Integer.parseInt(columns[0]), LocalDateTime.parse(columns[1]), columns[2]);
			shows.add(show);
		});
	}

	private void loadBookings(Path jsonPath) {
		bookings.clear();
		ShowIndex index = new ShowIndex(shows);
		try {
			Files.readAllLines(jsonPath, StandardCharsets.UTF_8).forEach(line -> {
				String[] columns = line.split(";");
				int bookingId = Integer.parseInt(columns[0]);
				int showId = Integer.parseInt(columns[1]);
				int numberOfSeats = Integer.parseInt(columns[2]);
				Show show = index.getShow(showId);
				if (show == null) {
					LOG.warn("Show with id {} not found", showId);
				}
				Booking booking = Booking.createBooking(bookingId, show, numberOfSeats);
				bookings.add(booking);
			});
		} catch (IOException e) {
			throw new IllegalStateException("Cannot load bookings", e);
		}
	}

	private static class ShowIndex {

		private final Map<Integer, List<Show>> showsById;

        private ShowIndex(List<Show> shows) {
            showsById = buildIndex(shows);
        }

        Show getShow(int showId) {
			List<Show> shows = showsById.get(showId);
			return shows.get(0);
		}

		private Map<Integer, List<Show>> buildIndex(List<Show> shows) {
			return shows.stream().collect(
				Collectors.groupingBy(Show::getId));
		}
	}
}
