package com.example.bestesttheaters.data;

import com.example.bestesttheaters.api.BookingDto;
import com.example.bestesttheaters.api.BookingRequestDto;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	private final InMemoryRepository repository;
	private ShowIndex showIndex;

	public BookingService(InMemoryRepository repository) {
		this.repository = repository;
	}

	public BookingDto getBookingDto(BookingRequestDto bookingRequest) {
		if (showIndex == null) {
			showIndex = new ShowIndex(repository.findAll());
		}
		Show show = showIndex.getShow(bookingRequest.showId());
		if (show == null) {
			throw new IllegalArgumentException(String.format("Unknown show ID: %d", bookingRequest.showId()));
		}
		BookingStatus booked = getBookingStatus(bookingRequest, show);
		int newBookingId = repository.findAllBookings().size() + 1;
		repository.saveBooking(Booking.createBooking(newBookingId, show, bookingRequest.numberOfTickets(), booked));
		return new BookingDto(newBookingId, bookingRequest.showId(), bookingRequest.numberOfTickets(), booked);
	}

	private static BookingStatus getBookingStatus(BookingRequestDto bookingRequest, Show show) {
		assert show != null;
		if (bookingRequest.numberOfTickets() > show.getCapacity()) {
			return BookingStatus.CANCELLED;
		}
		return BookingStatus.BOOKED;
	}
}
