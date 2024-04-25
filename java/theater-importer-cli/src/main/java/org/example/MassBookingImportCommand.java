package org.example;

import org.springframework.shell.command.annotation.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Command(group = "bookings")
public class MassBookingImportCommand {

    @Command(command = "import-bookings")
    public void massImport(String bookingFile, int startBookingId) throws IOException {
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

        Path bookingPath = Paths.get(bookingFile);
        AtomicInteger bookingId = new AtomicInteger(startBookingId);
        Files.readAllLines(bookingPath).stream()
                .map(line -> line.split(";"))
                .map(columns -> new BookingRequest(
                        Integer.parseInt(columns[0]),
                        Integer.parseInt(columns[1])))
                .forEach(request -> {
                    Booking booking = doBooking(request, index, bookingId.getAndIncrement());
                    System.out.println(booking.toCsv());
                });
    }

    private Booking doBooking(BookingRequest request, Map<Integer, Show> index, int newBookingId) {
        Show show = index.get(request.showId());
        if (show == null) {
            throw new IllegalArgumentException(String.format("Show with ID %d not found", request.showId()));
        }
        if (request.numberOfTickets() > show.capacity()) {
            return new Booking(newBookingId, show.showID(), request.numberOfTickets(), "CANCELLED");
        }
        return new Booking(newBookingId, show.showID(), request.numberOfTickets(), "BOOKED");
    }
}
