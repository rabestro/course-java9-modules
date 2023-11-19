package com.epam.jmp.application.dto.response;

import com.epam.jmp.dto.BankCard;

public record BankCardDetails(String number, String user) {
	public BankCardDetails(BankCard card) {
		this(card.number(), card.user().name() + " " + card.user().surname());
	}
}
