package com.epam.jmp.application.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp.api.Bank;
import com.epam.jmp.application.dto.request.BankCardRequest;
import com.epam.jmp.application.dto.request.SubscriptionRequest;
import com.epam.jmp.application.dto.response.BankCardDetails;
import com.epam.jmp.application.dto.response.SubscriptionDetails;
import com.epam.jmp.application.exception.NoSuchSubscription;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

@RestController
@RequestMapping("/api")
public class ServiceController {
	private final Bank bank = ServiceLoader.load(Bank.class).findFirst()
		.orElseThrow(() -> new IllegalStateException("No implementation of Bank service found."));
	private final Service service = ServiceLoader.load(Service.class).findFirst()
		.orElseThrow(() -> new IllegalStateException("No implementation of Service service found."));

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}

	@GetMapping("/user/average-age")
	public double getAverageUserAge() {
		return service.getAverageUserAge();
	}

	@PostMapping("/user/subscription")
	public void subscribe(@RequestBody SubscriptionRequest request) {
		bank.findBankCardByNumber(request.cardNumber())
			.ifPresentOrElse(service::subscribe, () -> {
				throw new NoSuchElementException(
					"Bank card %s not found".formatted(request.cardNumber()));
			});
	}

	@GetMapping("/user/subscription/{cardNumber}")
	public ResponseEntity<SubscriptionDetails> getSubscriptionsByBankCardNumber(@PathVariable String cardNumber) {
		return service.getSubscriptionByBankCardNumber(cardNumber)
			.map(SubscriptionDetails::new)
			.map(ResponseEntity::ok)
			.orElseThrow(NoSuchSubscription::new);
	}

	@PostMapping(value = "/card")
	public ResponseEntity<?> createBankCard(@RequestBody BankCardRequest request) {
		var card = bank.createBankCard(request.user(), request.type());
		return ResponseEntity.ok(card.number());
	}

	@GetMapping(value = "/card/{cardNumber}")
	public ResponseEntity<BankCardDetails> getBankCardByNumber(@PathVariable String cardNumber) {
		return bank.findBankCardByNumber(cardNumber)
			.map(BankCardDetails::new)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(value = "/card")
	public List<BankCardDetails> getAllBankCards() {
		return bank.getAllBankCards().map(BankCardDetails::new).toList();
	}
}
