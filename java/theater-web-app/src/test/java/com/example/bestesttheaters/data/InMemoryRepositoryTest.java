package com.example.bestesttheaters.data;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositoryTest {

	@Test
	void defaultLoad() {
		var repository = new InMemoryRepository();
		List<Show> all = repository.findAll();
		assertEquals(9, all.size());
	}
}
