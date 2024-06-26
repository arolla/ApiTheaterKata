package com.example.bestesttheaters.api;

import com.example.bestesttheaters.data.BookingService;
import com.example.bestesttheaters.data.InMemoryRepository;
import com.example.bestesttheaters.data.WaitListItem;
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
	public ResponseEntity<WaitListItemDto> waitList(@PathVariable("itemId") UUID itemId) {
		WaitListItem waitListItem = repository.getWaitListItemDto(itemId);
		if (waitListItem == null) {
			return ResponseEntity.notFound().build();
		}
		WaitListItemDto waitListItemDto = map(waitListItem);

		Link selfLink = linkTo(ApiController.class).slash("wait-list").slash(waitListItemDto.getItemId()).withSelfRel();
		waitListItemDto.add(selfLink);
		return ResponseEntity.ok(waitListItemDto);
	}

	@GetMapping("/wait-list")
	public WaitListDto listWaitList() {
		List<WaitListItemDto> list = repository.findAllWaitListItems().stream()
			.map(this::map)
			.toList();
		list.forEach(waitListItemDto -> {
			Link selfLink = linkTo(ApiController.class).slash("wait-list").slash(waitListItemDto.getItemId()).withSelfRel();
			waitListItemDto.add(selfLink);
		});
		WaitListDto waitListDto = new WaitListDto(list);
		Link selfLink = linkTo(ApiController.class).slash("wait-list").withSelfRel();
		waitListDto.add(selfLink);
		return waitListDto;
	}


	@PostMapping("/wait-list")
	public WaitListItemDto waitList(@RequestBody BookingRequestDto bookingRequest) {
		WaitListItem waitListItem = repository.newWaitListItemDto(bookingRequest.showId(), bookingRequest.numberOfTickets());
		WaitListItemDto waitListItemDto = map(waitListItem);
		Link selfLink = linkTo(ApiController.class).slash("wait-list").slash(waitListItemDto.getItemId()).withSelfRel();
		waitListItemDto.add(selfLink);
		return waitListItemDto;
	}

	private WaitListItemDto map(WaitListItem waitListItem) {
		return new WaitListItemDto(waitListItem.uuid(), waitListItem.showId(), waitListItem.numberOfTickets());
	}

}
