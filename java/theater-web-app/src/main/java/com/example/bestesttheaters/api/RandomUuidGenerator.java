package com.example.bestesttheaters.api;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomUuidGenerator implements UuidGenerator {
	@Override
	public UUID newUuid() {
		return UUID.randomUUID();
	}
}
