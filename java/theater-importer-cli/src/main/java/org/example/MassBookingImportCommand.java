package org.example;

import org.springframework.shell.command.annotation.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Command(group = "bookings")
public class MassBookingImportCommand {

    @Command(command = "import-bookings")
    public void massImport(String bookingFile) throws IOException {
        Path showsPath = Paths.get("../theater-web-app/shows.csv");
        Map<Integer, Show> index = Files.readAllLines(showsPath).stream()
                .map(line -> line.split(";"))
                .map(parts -> new Show(
                        Integer.parseInt(parts[0]),
                        LocalDateTime.parse(parts[1]),
                        parts[2],
                        Integer.parseInt(parts[3])))
                .collect(Collectors.toMap(Show::showID, show -> show));
        System.out.println("Loaded " + index.size() + " shows");
    }

    private Booking doBooking(BookingRequest request, Map<Integer, Show> index) {
        Show show = index.get(request.showId());

        return new Booking();
    }
}
