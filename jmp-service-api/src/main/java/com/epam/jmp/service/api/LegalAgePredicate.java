package com.epam.jmp.service.api;

import java.time.Clock;
import java.time.LocalDate;
import java.util.function.Predicate;

import com.epam.jmp.dto.User;

public class LegalAgePredicate implements Predicate<User> {
	private static final int LEGAL_AGE = 18;

	private final Clock clock;

	public LegalAgePredicate() {
		this.clock = Clock.systemDefaultZone();
	}

	public LegalAgePredicate(Clock clock) {
		this.clock = clock;
	}

	@Override
	public boolean test(User user) {
		if (user == null || user.birthday() == null) {
			return false;
		}
		return !LocalDate.now(clock)
			.minusYears(LEGAL_AGE)
			.isBefore(user.birthday());
	}
}
