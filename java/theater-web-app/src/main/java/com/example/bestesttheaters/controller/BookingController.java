/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.bestesttheaters.controller;

import com.example.bestesttheaters.data.Booking;
import com.example.bestesttheaters.data.InMemoryRepository;
import com.example.bestesttheaters.data.Show;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
class BookingController {

	private final InMemoryRepository showRepository;

	public BookingController(InMemoryRepository showRepository) {
		this.showRepository = showRepository;
	}

	@ModelAttribute("show")
	public Show loadShow(@PathVariable("showId") int showId) {
        return getShowById(showId).get();
	}

	private Optional<Show> getShowById(int showId) {
		return showRepository.findAll()
			.stream()
			.filter(show1 -> show1.getId() == showId)
			.findFirst();
	}

	@GetMapping("/shows/{showId}/booking/new")
	public String initNewVisitForm() {
		return "shows/createBookingForm";
	}

	@PostMapping("/shows/{showId}/booking/new")
	public String processNewBookingForm(@ModelAttribute("show") Show show,
										int numberOfTickets,
										RedirectAttributes redirectAttributes) {
		int id = showRepository.findAllBookings().size() + 1;
		Booking booking = Booking.createBooking(id, show, numberOfTickets);
		showRepository.saveBooking(booking);
		redirectAttributes.addFlashAttribute("message", "Your show has been booking successfully");
		return "redirect:/bookings.html";
	}

}
