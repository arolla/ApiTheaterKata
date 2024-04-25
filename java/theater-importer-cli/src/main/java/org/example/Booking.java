package org.example;

public record Booking(int newBookingId, int showId, int numberOfTickets, String status) {
    public String toCsv() {
        return String.format("%d;%d;%d;%s", newBookingId, showId, numberOfTickets, status);
    }
}
