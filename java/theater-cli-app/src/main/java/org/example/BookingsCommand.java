package org.example;

import org.springframework.shell.command.annotation.Command;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Command(group = "bookings")
public class BookingsCommand {

    private final WebClient httpClient;

    public BookingsCommand(WebClient httpClient) {
        this.httpClient = httpClient;
    }

    @Command(command = "display-bookings")
    public void displayShows() {
        httpClient.get()
                .uri("http://localhost:8080/api/v1/bookings")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }

    @Command(command = "book")
    public void bookShow(String showId, int numberOfTickets) {
        httpClient.post()
                .uri("http://localhost:8080/api/v1/bookings")
                .bodyValue(Map.of("showId", showId, "numberOfTickets", numberOfTickets))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }
}
