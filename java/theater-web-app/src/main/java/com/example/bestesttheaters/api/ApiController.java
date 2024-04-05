package com.example.bestesttheaters.api;

import com.example.bestesttheaters.data.BookingService;
import com.example.bestesttheaters.data.InMemoryRepository;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

	private final InMemoryRepository repository;
	private final BookingService bookingService;
	private final UuidGenerator uuidGenerator;

	public ApiController(InMemoryRepository repository, BookingService bookingService, UuidGenerator uuidGenerator) {
		this.repository = repository;
        this.bookingService = bookingService;
        this.uuidGenerator = uuidGenerator;
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
		Link selfLink = linkTo(ApiController.class).slash("shows").withSelfRel();
		ShowsDto showsDto = new ShowsDto(shows);
		showsDto.add(selfLink);
		return showsDto;
	}

	@GetMapping("/bookings")
	public BookingsDto listBookings() {
		return new BookingsDto(List.of());
	}

	@PostMapping("/bookings")
	public BookingDto book(@RequestBody BookingRequestDto bookingRequest) {
		return bookingService.getBookingDto(bookingRequest);
	}

	@GetMapping("/wait-list/{itemId}")
	public WaitListItemDto waitList(@PathVariable("itemId") UUID itemId) {
		UUID uuid = uuidGenerator.newUuid();
		WaitListItemDto waitListItemDto = new WaitListItemDto(1, 2);
		Link selfLink = linkTo(ApiController.class).slash("wait-list").slash(uuid).withSelfRel();
		waitListItemDto.add(selfLink);
		return waitListItemDto;
	}

	@PostMapping("/wait-list")
	public WaitListItemDto waitList(@RequestBody BookingRequestDto bookingRequest) {
		WaitListItemDto waitListItemDto = new WaitListItemDto(bookingRequest.showId(), bookingRequest.numberOfTickets());
		UUID waitListItemId = uuidGenerator.newUuid();
		Link selfLink = linkTo(ApiController.class).slash("wait-list").slash(waitListItemId).withSelfRel();
		waitListItemDto.add(selfLink);
		return waitListItemDto;
	}

}
