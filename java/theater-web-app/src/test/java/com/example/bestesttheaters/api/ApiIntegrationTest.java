package com.example.bestesttheaters.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
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
                    {
                    	"shows":[
                    		{"id":1,"title":"Miraculous"},
                    		{"id":2,"title":"Les As de la jungle 2"},
                    		{"id":3,"title":"Anatomie d'une chute"},
                    		{"id":4,"title":"Tempête"},
                    		{"id":5,"title":"Passages"},
                    		{"id":6,"title":"Mon chat et moi"},
                    		{"id":7,"title":"Les Choses simples"},
                    		{"id":8,"title":"Le Bleu du caftan"},
                    		{"id":9,"title":"Mon crime"}]
                    }""");
	}

	@Test
	void listBookings(@Autowired WebTestClient webTestClient) {
		webTestClient.get()
			.uri("/api/v1/bookings")
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.json("""
				{
					"bookings": []
				}
				""");
	}

	@Test
	void book(@Autowired WebTestClient webTestClient) {
		webTestClient.post()
			.uri("/api/v1/bookings")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue("""
				{
					"showId": 1,
					"numberOfTickets": 2
				}""")
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.json("""
                {
					"bookingId": 1,
					"showId": 1,
					"numberOfTickets": 2
				}""");
	}

}
