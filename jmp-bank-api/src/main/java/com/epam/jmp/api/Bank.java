package com.epam.jmp.api;

import java.util.Optional;
import java.util.stream.Stream;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;


public interface Bank {
	BankCard createBankCard(User user, BankCardType type);

	Optional<BankCard> findBankCardByNumber(String cardNumber);

	Stream<BankCard> getAllBankCards();
}
