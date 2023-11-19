package com.epam.jmp.application.controller;

import java.util.ServiceLoader;

import com.epam.jmp.dto.BankCard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp.api.Bank;

@RestController
@RequestMapping("/api")
public class BankController {
	private final Bank bank = ServiceLoader.load(Bank.class).findFirst().orElseThrow();

	@PostMapping(value = "/card")
	public ResponseEntity<?> createBankCard(@RequestBody BankCardRequest request) {
		var card = bank.createBankCard(request.user(), request.type());
		return ResponseEntity.ok(card.number());
	}

	@GetMapping(value = "/card/{cardNumber}")
	public ResponseEntity<?> getBankCardByNumber(@PathVariable String cardNumber) {
		return bank.findBankCardByNumber(cardNumber)
			.map(BankCard::user)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}
}
