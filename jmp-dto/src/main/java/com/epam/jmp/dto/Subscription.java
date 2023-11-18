package com.epam.jmp.dto;

import java.time.LocalDate;
import java.util.Objects;

public record Subscription(String bankcard, LocalDate startDate) {
	public Subscription {
		Objects.requireNonNull(bankcard, "Bankcard cannot be null");
		Objects.requireNonNull(startDate, "Start date cannot be null");
	}
}
