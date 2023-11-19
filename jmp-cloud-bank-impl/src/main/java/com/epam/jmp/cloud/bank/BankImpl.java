package com.epam.jmp.cloud.bank;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.epam.jmp.api.Bank;
import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.CreditBankCard;
import com.epam.jmp.dto.DebitBankCard;
import com.epam.jmp.dto.User;


public class BankImpl implements Bank {
	private final Set<BankCard> bankCards = new HashSet<>();

	@Override
	public BankCard createBankCard(User user, BankCardType type) {
		var cardNumber = UUID.randomUUID().toString();

		var card = switch (type) {
			case CREDIT -> new CreditBankCard(cardNumber, user);
			case DEBIT -> new DebitBankCard(cardNumber, user);
		};
		bankCards.add(card);
		return card;
	}

	@Override
	public Optional<BankCard> findBankCardByNumber(String cardNumber) {
		return bankCards.stream()
			.filter(card -> card.number().equals(cardNumber))
			.findFirst();
	}
}
