package com.epam.jmp.service.api;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {
	int MATURITY_AGE = 18;

	static boolean isPayableUser(User user) {
		return user.birthday()
			.plusYears(MATURITY_AGE)
			.isBefore(LocalDate.now());
	}

	void subscribe(BankCard bankCard);

	Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

	List<User> getAllUsers();

	default double getAverageUserAge() {
		return getAllUsers().stream()
			.map(User::birthday)
			.mapToDouble(birthday -> LocalDate.now().getYear() - birthday.getYear())
			.average()
			.orElse(0);
	}

	List<Subscription> getAllSubscriptionsByCondition(Predicate<? super Subscription> filter);
}
