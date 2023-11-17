package com.epam.engx.jmp.cloud.service;


import com.epam.engx.jmp.dto.BankCard;
import com.epam.engx.jmp.dto.Subscription;
import com.epam.engx.jmp.dto.User;
import com.epam.engx.jmp.service.api.Service;

import java.util.List;
import java.util.Optional;

public class ServiceImpl implements Service {

	@Override
	public void subscribe(BankCard bankCard) {

	}

	@Override
	public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
		return Optional.empty();
	}

	@Override
	public List<User> getAllUsers() {
		return null;
	}
}
