package com.example.bestesttheaters.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
	@GetMapping("/shows")
	public ShowsDto listShows() {
		return new ShowsDto(List.of());
	}

	@GetMapping("/bookings")
	public BookingsDto listBookings() {
		return new BookingsDto(List.of());
	}

	@PostMapping("/bookings")
	public BookingDto book(@RequestBody BookingRequestDto bookingRequest) {
		return new BookingDto(1, bookingRequest.showId(), bookingRequest.numberOfTickets());
	}
}
