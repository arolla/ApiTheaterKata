package com.example.bestesttheaters.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApiIntegrationTest {

	@Test
	void listShows(@Autowired WebTestClient webTestClient) {
		webTestClient.get()
				.uri("/api/v1/shows")
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.json("""
                        {}
                        """);
	}


}
