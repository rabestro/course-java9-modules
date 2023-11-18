package com.epam.jmp.app.controller;

import com.epam.jmp.api.Bank;
import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ServiceLoader;

@RestController
@RequestMapping("/api")
public class Controller {
	private final Bank bank = ServiceLoader.load(Bank.class).findFirst().orElseThrow();

	@PostMapping(value = "/createCard")
	@ResponseBody
	public void createBankCard(User user, BankCardType type) {
		bank.createBankCard(user, type);
	}
}

record BankCardRequest(User user, BankCardType type) {
}
