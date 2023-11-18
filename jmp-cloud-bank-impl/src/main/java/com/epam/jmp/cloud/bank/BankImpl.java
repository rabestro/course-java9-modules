package com.epam.jmp.cloud.bank;

import com.epam.jmp.api.Bank;
import com.epam.jmp.cloud.bank.cardnumber.CardNumberSupplier;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.User;

import java.util.Objects;

public final class BankImpl implements Bank {

	@Override
	public BankCard createBankCard(User user, BankCardType type) {
		Objects.requireNonNull(user, "BankUser cannot be null");
		Objects.requireNonNull(type, "Bank card type cannot be null");

		var cardNumberSupplier = new CardNumberSupplier();
		var cardNumber = cardNumberSupplier.get();

		return switch (type) {
			case CREDIT -> new CreditBankCard(cardNumber, user);
			case DEBIT -> new DebitBankCard(cardNumber, user);
		};
	}
}
