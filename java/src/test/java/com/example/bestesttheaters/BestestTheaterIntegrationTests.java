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

package com.example.bestesttheaters;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.bestesttheaters.data.InMemoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BestestTheaterIntegrationTests {

	@LocalServerPort
	int port;

	@Autowired
	private InMemoryRepository showRepository;

	@Autowired
	private RestTemplateBuilder builder;

	@Test
	void testFindAll() throws Exception {
		showRepository.findAll();
		showRepository.findAll(); // served from cache
	}

	public static void main(String[] args) {
		SpringApplication.run(BesttestTheaterApplication.class, args);
	}

}
