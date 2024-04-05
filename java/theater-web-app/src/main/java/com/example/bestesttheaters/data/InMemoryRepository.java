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

import com.example.bestesttheaters.api.UuidGenerator;
import com.example.bestesttheaters.api.WaitListItemDto;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class InMemoryRepository {

	private final static Logger LOG = LoggerFactory.getLogger(InMemoryRepository.class);

	private final List<Show> shows = new ArrayList<>();
	private final List<Booking> bookings;
	private final String showsFile;
	private final String bookingsFile;
	private final UuidGenerator uuidGenerator;
	private final Map<UUID, WaitListItem> waitListIndex;

	public InMemoryRepository(@Value("${shows.file}") String showsFile, @Value("${bookings.file}") String bookingsFile, UuidGenerator uuidGenerator) {
		this.showsFile = showsFile;
		this.bookingsFile = bookingsFile;
        this.uuidGenerator = uuidGenerator;
        bookings = new ArrayList<>();
        waitListIndex = new HashMap<>();
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

		shows.add(testShow());

		Files.readAllLines(jsonFile, StandardCharsets.UTF_8).forEach(line -> {
			String[] columns = line.split(";");
			int columnCount = columns.length;
			int expectedCount = 4;
			if (columnCount > expectedCount) {
				LOG.warn("Line '{}' has extra column(s). Expect {} columns but has {}. Ignore unknown columns", line, expectedCount, columnCount);
			}
			int id = Integer.parseInt(columns[0]);
			LocalDateTime date = LocalDateTime.parse(columns[1]);
			String title = columns[2];
			int capacity = Integer.parseInt(columns[3]);
			Show show = Show.createShow(id, date, title, capacity);
			shows.add(show);
		});
	}

	private static Show testShow() {
		LocalDateTime tomorrow8pm = LocalDateTime.now().withHour(20).withMinute(0).withSecond(0).plus(Duration.ofDays(1));
		return Show.createShow(0, tomorrow8pm, "Cyrano de Bergerac", 100);
	}

	private void loadBookings(Path jsonPath) {
		bookings.clear();
		ShowIndex index = new ShowIndex(shows);
		try {
			Files.readAllLines(jsonPath, StandardCharsets.UTF_8).forEach(line -> {
				String[] columns = line.split(";");
				int expectedColumn = 4;
				if (columns.length > expectedColumn) {
					LOG.warn("Line '{}' has extra column(s). Expect {} columns but has {}. Ignore unknown columns", line, expectedColumn, columns.length);
				}
				int bookingId = Integer.parseInt(columns[0]);
				int showId = Integer.parseInt(columns[1]);
				int numberOfSeats = Integer.parseInt(columns[2]);
				BookingStatus status = BookingStatus.valueOf(columns[3]);
				Show show = index.getShow(showId);
				if (show == null) {
					LOG.warn("Show with id {} not found", showId);
				} else {
					Booking booking = Booking.createBooking(bookingId, show, numberOfSeats, status);
					bookings.add(booking);
				}
			});
		} catch (IOException e) {
			throw new IllegalStateException("Cannot load bookings", e);
		}
	}

	public WaitListItem newWaitListItemDto(int showId, int numberOfTickets) {
		UUID uuid = uuidGenerator.newUuid();
		WaitListItem waitListItemDto = new WaitListItem(uuid, showId, numberOfTickets);
		saveWaitListItem(uuid, waitListItemDto);
		return waitListItemDto;
	}

	public WaitListItem getWaitListItemDto(UUID itemId) {
		return waitListIndex.get(itemId);
	}

	void saveWaitListItem(UUID uuid, WaitListItem waitListItemDto) {
		waitListIndex.put(uuid, waitListItemDto);
	}

}
