package com.example.bestesttheaters.data;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryRepositoryTest {

	private static final int TEST_SHOW_COUNT = 1;

	@Test
	void init() throws URISyntaxException, IOException {
		Path showsPath = getJsonPath("/shows.csv");
		Path bookingsPath = getJsonPath("/bookings.csv");

		var repository = new InMemoryRepository(showsPath.toString(), bookingsPath.toString());
		repository.init();

		List<Show> allShows = repository.findAll();
		List<Booking> allBookings = repository.findAllBookings();

		assertEquals(9 + TEST_SHOW_COUNT, allShows.size());
		assertEquals(3, allBookings.size());
	}

	private static Path getJsonPath(String resourceName) throws URISyntaxException {
		URL resource = InMemoryRepositoryTest.class.getResource(resourceName);
		if (resource == null) {
			throw new IllegalArgumentException("Resource not found: " + resourceName);
		}
		return Paths.get(resource.toURI());
	}
}
