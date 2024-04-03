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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryRepository {

	private final List<Show> shows = new ArrayList<>();
	private final List<Booking> bookings;
	private final String jsonFile;

	public InMemoryRepository(@Value("${shows.file}") String jsonFile) {
		this.jsonFile = jsonFile;
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
		load(Path.of(jsonFile));
	}

	public void load(Path jsonFile) throws IOException {
		shows.clear();
		Files.readAllLines(jsonFile, StandardCharsets.UTF_8).forEach(line -> {
			String[] columns = line.split(";");
			Show show = Show.createShow(Integer.parseInt(columns[0]), LocalDateTime.parse(columns[1]), columns[2]);
			shows.add(show);
		});
	}
}
