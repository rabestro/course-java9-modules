package com.epam.engx.jmp.dto;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

public record User(String name, String surname, LocalDate birthday) {
	public User {
		requireNonNull(name, "Name cannot be null");
		requireNonNull(surname, "Surname cannot be null");
		requireNonNull(birthday, "Birthday cannot be null");
	}
}
