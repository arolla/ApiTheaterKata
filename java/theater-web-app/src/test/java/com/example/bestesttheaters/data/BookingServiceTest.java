package com.example.bestesttheaters.data;

import com.example.bestesttheaters.api.BookingDto;
import com.example.bestesttheaters.api.BookingRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookingServiceTest {

	private static final Show MATRIX = Show.createShowMediumCapacity(1, LocalDateTime.parse("2021-12-01T20:00:00"), "The Matrix");

	private InMemoryRepository repository;

	@BeforeEach
	void setUp() {
        repository = mock(InMemoryRepository.class);
        when(repository.findAll()).thenReturn(List.of(MATRIX));
	}

	@Test
	void unknownShowId() {
		var bookingService = new BookingService(repository);
		var bookingDto = bookingService.getBookingDto(new BookingRequestDto(42, 2));
		assertEquals(new BookingDto(1, 42, 2, BookingStatus.UNKNOWN_SHOW), bookingDto);
	}

	@Test
	void bookingSuccess() {
		var bookingService = new BookingService(repository);
		var bookingDto = bookingService.getBookingDto(new BookingRequestDto(1, 2));
		assertEquals(new BookingDto(1, 1, 2, BookingStatus.BOOKED), bookingDto);
	}

	@Test
	void bookingCancelled() {
		var bookingService = new BookingService(repository);
		var bookingDto = bookingService.getBookingDto(new BookingRequestDto(1, 200));
		assertEquals(new BookingDto(1, 1, 200, BookingStatus.CANCELLED), bookingDto);
	}

}
