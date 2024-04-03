package com.example.bestesttheaters.data;

import com.example.bestesttheaters.api.BookingDto;
import com.example.bestesttheaters.api.BookingRequestDto;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	private final InMemoryRepository repository;

    public BookingService(InMemoryRepository repository) {
        this.repository = repository;
    }

    public BookingDto getBookingDto(BookingRequestDto bookingRequest) {
		int newBookingId = repository.findAllBookings().size() + 1;
		return new BookingDto(newBookingId, bookingRequest.showId(), bookingRequest.numberOfTickets(), BookingStatus.BOOKED);
	}
}
