package com.example.bestesttheaters.api;

import com.example.bestesttheaters.data.BookingStatus;

public record BookingDto(int bookingId, int showId, int numberOfTickets, BookingStatus status) {
}
