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
import com.epam.jmp.application.exception.NoSuchSubscription;
import com.epam.jmp.dto.Subscription;
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

	@PostMapping("/user/subscribe")
	public void subscribe(@RequestBody String cardNumber) {
		bank.findBankCardByNumber(cardNumber)
			.ifPresentOrElse(service::subscribe, () -> {
				throw new NoSuchElementException("Bank card %s not found".formatted(cardNumber));
			});
	}

	@GetMapping("/user/subscription/{cardNumber}")
	public ResponseEntity<Subscription> getSubscriptionsByBankCardNumber(@PathVariable String cardNumber) {
		return service.getSubscriptionByBankCardNumber(cardNumber)
			.map(ResponseEntity::ok)
			.orElseThrow(NoSuchSubscription::new);
	}

}
