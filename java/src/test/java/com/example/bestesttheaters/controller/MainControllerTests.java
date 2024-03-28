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

import com.example.bestesttheaters.data.InMemoryRepository;
import com.example.bestesttheaters.data.Show;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
@DisabledInNativeImage
@DisabledInAotMode
class MainControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InMemoryRepository showRepository;

	private Show james() {
		Show james = new Show();
		james.setTitle("Carter");
		james.setId(1);
		return james;
	}

	private Show helen() {
		Show helen = new Show();
		helen.setTitle("Leary");
		helen.setId(2);
		return helen;
	}

	@BeforeEach
	void setup() {
		given(this.showRepository.findAll()).willReturn(Lists.newArrayList(james(), helen()));
	}

	@Test
	void testDisplayShowListHtml() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/shows.html?page=1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("listShows"))
			.andExpect(view().name("shows/showList"));
	}

	@Test
	void testDisplayBookingListHtml() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/bookings.html?page=1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("listBookings"))
			.andExpect(view().name("shows/bookingList"));
	}

}
