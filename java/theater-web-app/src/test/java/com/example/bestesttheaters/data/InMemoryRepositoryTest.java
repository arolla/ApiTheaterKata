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

	@Test
	void defaultLoad() {
		var repository = new InMemoryRepository();
		List<Show> all = repository.findAll();
		assertEquals(9, all.size());
	}

	@Test
	void fileLoad() throws URISyntaxException, IOException {
		var repository = new InMemoryRepository();
		Path jsonPath = getJsonPath("/shows.csv");
		repository.load(jsonPath);
		List<Show> all = repository.findAll();
		assertEquals(9, all.size());
	}

	private static Path getJsonPath(String resourceName) throws URISyntaxException {
		URL resource = InMemoryRepositoryTest.class.getResource(resourceName);
		if (resource == null) {
			throw new IllegalArgumentException("Resource not found: " + resourceName);
		}
		return Paths.get(resource.toURI());
	}
}
