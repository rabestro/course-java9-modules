package com.epam.jmp.service.api;

import java.util.List;
import java.util.Optional;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.Subscription;
import com.epam.jmp.dto.User;

public interface Service {
	void subscribe(BankCard bankCard);

	Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

	List<User> getAllUsers();
}
