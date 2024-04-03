package com.example.bestesttheaters.data;

import com.example.bestesttheaters.api.BookingDto;
import com.example.bestesttheaters.api.BookingRequestDto;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	private final InMemoryRepository repository;
	private final ShowIndex showIndex;

	public BookingService(InMemoryRepository repository) {
		this.repository = repository;
		showIndex = new ShowIndex(repository.findAll());
	}

	public BookingDto getBookingDto(BookingRequestDto bookingRequest) {
		Show show = showIndex.getShow(bookingRequest.showId());
		BookingStatus booked = getBookingStatus(bookingRequest, show);
		int newBookingId = repository.findAllBookings().size() + 1;
		return new BookingDto(newBookingId, bookingRequest.showId(), bookingRequest.numberOfTickets(), booked);
	}

	private static BookingStatus getBookingStatus(BookingRequestDto bookingRequest, Show show) {
		if (show == null) {
			return BookingStatus.UNKNOWN_SHOW;
		}
		if (bookingRequest.numberOfTickets() > show.getCapacity()) {
			return BookingStatus.CANCELLED;
		}
		return BookingStatus.BOOKED;
	}
}
