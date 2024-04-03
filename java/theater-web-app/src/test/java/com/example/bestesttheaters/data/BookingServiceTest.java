package com.example.bestesttheaters.data;

import com.example.bestesttheaters.api.BookingDto;
import com.example.bestesttheaters.api.BookingRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class BookingServiceTest {
	@Test
	void bookingSuccess() {
		var mock = mock(InMemoryRepository.class);
		var bookingService = new BookingService(mock);
		var bookingDto = bookingService.getBookingDto(new BookingRequestDto(1, 2));
		assertEquals(new BookingDto(1, 1, 2), bookingDto);
	}
}
