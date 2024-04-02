package org.example;

import org.springframework.shell.command.annotation.Command;
import org.springframework.web.reactive.function.client.WebClient;

@Command(group = "shows")
public class DisplayShowsCommand {

    private final WebClient httpClient;

    public DisplayShowsCommand(WebClient httpClient) {
        this.httpClient = httpClient;
    }
    @Command(command = "display-shows")
    public void displayShows() {
        httpClient.get()
                .uri("http://localhost:8080/api/v1/shows")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(System.out::println);
    }
}
