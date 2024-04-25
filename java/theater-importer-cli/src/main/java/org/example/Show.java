package org.example;

import java.time.LocalDateTime;

public record Show(int showID, LocalDateTime date, String title, int capacity) {
}

