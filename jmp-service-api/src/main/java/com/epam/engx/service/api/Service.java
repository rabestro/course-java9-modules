package com.epam.engx.service.api;

import java.util.List;
import java.util.Optional;

import com.epam.engx.jmp.dto.BankCard;
import com.epam.engx.jmp.dto.Subscription;
import com.epam.engx.jmp.dto.User;

public interface Service {
	void subscribe(BankCard bankCard);

	Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

	List<User> getAllUsers();
}
