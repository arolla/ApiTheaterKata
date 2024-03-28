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

import java.util.List;

import com.example.bestesttheaters.data.Booking;
import com.example.bestesttheaters.data.InMemoryRepository;
import com.example.bestesttheaters.data.Show;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
class MainController {

	private final InMemoryRepository showRepository;

	public MainController(InMemoryRepository showRepository) {
		this.showRepository = showRepository;
	}

	@GetMapping("/shows.html")
	public String displayShowList(@RequestParam(defaultValue = "1") int page, Model model) {
		List<Show> listShows = showRepository.findAll();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", 1);
		model.addAttribute("totalItems", listShows.size());
		model.addAttribute("listShows", listShows);
		return "shows/showList";
	}

	@GetMapping("/bookings.html")
	public String displayBookingList(@RequestParam(defaultValue = "1") int page, Model model) {
		List<Booking> listShows = showRepository.findAllBookings();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", 1);
		model.addAttribute("totalItems", listShows.size());
		model.addAttribute("listBookings", listShows);
		return "shows/bookingList";
	}

}
