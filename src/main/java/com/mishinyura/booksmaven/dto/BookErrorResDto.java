package com.mishinyura.booksmaven.dto;

import java.time.LocalDateTime;

public record BookErrorResDto(
        String message,
        LocalDateTime date) {
}
