package com.example.bestesttheaters.data;

import java.util.UUID;

public record WaitListItem(UUID uuid, int showId, int numberOfTickets) {
}
