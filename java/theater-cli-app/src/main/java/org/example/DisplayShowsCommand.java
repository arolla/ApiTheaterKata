package org.example;

import org.springframework.shell.command.annotation.Command;

@Command(group = "shows")
public class DisplayShowsCommand {
    @Command(command = "display-shows")
    public void displayShows() {
        System.out.println("Displaying shows...");
    }
}
