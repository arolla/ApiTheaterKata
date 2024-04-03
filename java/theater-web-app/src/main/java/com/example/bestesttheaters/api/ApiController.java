package com.example.bestesttheaters.api;

import com.example.bestesttheaters.data.BookingService;
import com.example.bestesttheaters.data.InMemoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

	private final InMemoryRepository repository;
	private final BookingService bookingService;

	public ApiController(InMemoryRepository repository, BookingService bookingService) {
		this.repository = repository;
        this.bookingService = bookingService;
    }

	@GetMapping("/shows/{showId}")
	public ResponseEntity<ShowDto> getShow(@PathVariable("showId") int showId) {
        return repository.findAll().stream()
            .filter(show -> show.getId() == showId)
            .map(show -> new ShowDto(show.getId(), show.getTitle(), show.getDate()))
            .findFirst()
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
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
		return bookingService.getBookingDto(bookingRequest);
	}

}
