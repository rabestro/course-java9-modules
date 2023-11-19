package com.epam.jmp.application.controller;

import com.epam.jmp.api.Bank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<?> createBankCard(@RequestBody BankCardRequest request) {
		var card = bank.createBankCard(request.user(), request.type());
		return ResponseEntity.ok(card.number());
	}

	@GetMapping(value = "/createCard")
	@ResponseBody
	public String createBankCard() {
		return "Hello World!";
	}
}
