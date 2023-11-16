package com.epam.engx.jmp.api;

import com.epam.engx.jmp.dto.BankCard;
import com.epam.engx.jmp.dto.BankCardType;
import com.epam.engx.jmp.dto.User;

public interface Bank {
	BankCard createBankCard(User user, BankCardType type);
}