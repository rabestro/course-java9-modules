package com.epam.engx.jmp.cloud.bank.impl;

import com.epam.engx.jmp.bankapi.Bank;
import com.epam.engx.jmp.dto.BankCard;
import com.epam.engx.jmp.dto.BankCardType;
import com.epam.engx.jmp.dto.CreditBankCard;
import com.epam.engx.jmp.dto.DebitBankCard;
import com.epam.engx.jmp.dto.User;

import java.util.Objects;

public final class BankCloudImpl implements Bank {

	@Override
	public BankCard createBankCard(User user, BankCardType type) {
		Objects.requireNonNull(user, "User cannot be null");
		Objects.requireNonNull(type, "Bank card type cannot be null");

		var cardNumberSupplier = new LuhnCardNumberSupplier();
		var cardNumber = cardNumberSupplier.get();

		return switch (type) {
			case CREDIT -> new CreditBankCard(cardNumber, user);
			case DEBIT -> new DebitBankCard(cardNumber, user);
		};
	}
}
