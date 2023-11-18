package com.epam.jmp.cloud.bank;

import java.util.UUID;

import com.epam.jmp.api.Bank;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.User;

public final class BankImpl implements Bank {

	@Override
	public BankCard createBankCard(User user, BankCardType type) {
		var cardNumber = UUID.randomUUID().toString();

		return switch (type) {
			case CREDIT -> new CreditBankCard(cardNumber, user);
			case DEBIT -> new DebitBankCard(cardNumber, user);
		};
	}
}
