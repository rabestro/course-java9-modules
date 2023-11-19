package com.epam.jmp.api;

import com.epam.jmp.dto.BankCard;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;

import java.util.Optional;


public interface Bank {
	BankCard createBankCard(User user, BankCardType type);

	Optional<BankCard> findBankCardByNumber(String cardNumber);
}
