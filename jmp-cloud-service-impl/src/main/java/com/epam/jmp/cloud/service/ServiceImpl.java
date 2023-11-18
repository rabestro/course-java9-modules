package com.epam.jmp.cloud.service;


import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceImpl implements Service {
	private final List<Subscription> subscriptions = new ArrayList<>();

	@Override
	public void subscribe(BankCard bankCard) {
		subscriptions.add(new Subscription(bankCard, LocalDate.now()));
	}

	@Override
	public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
		return subscriptions.stream()
			.filter(subscription -> subscription.bankcard().number().equals(cardNumber))
			.findFirst();
	}

	@Override
	public List<User> getAllUsers() {
		return subscriptions.stream()
			.map(Subscription::bankcard)
			.map(BankCard::user)
			.distinct()
			.toList();
	}
}
