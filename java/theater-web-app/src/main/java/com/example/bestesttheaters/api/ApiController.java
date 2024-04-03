package com.example.bestesttheaters.api;

import com.example.bestesttheaters.data.InMemoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

	private final InMemoryRepository repository;

	public ApiController(InMemoryRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/shows")
	public ShowsDto listShows() {
		List<ShowDto> shows = repository.findAll().stream()
			.map(show -> new ShowDto(show.getId(), show.getTitle(), show.getDate()))
			.toList();
		return new ShowsDto(shows);
	}

	@GetMapping("/bookings")
	public BookingsDto listBookings() {
		return new BookingsDto(List.of());
	}

	@PostMapping("/bookings")
	public BookingDto book(@RequestBody BookingRequestDto bookingRequest) {
		int newBookingId = repository.findAllBookings().size() + 1;
		return new BookingDto(newBookingId, bookingRequest.showId(), bookingRequest.numberOfTickets());
	}
}
