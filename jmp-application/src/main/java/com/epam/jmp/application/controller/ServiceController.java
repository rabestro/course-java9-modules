package com.epam.jmp.application.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.jmp.api.Bank;
import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

@RestController
@RequestMapping("/api")
public class ServiceController {
	private final Bank bank = ServiceLoader.load(Bank.class).findFirst().orElseThrow();
	private final Service service = ServiceLoader.load(Service.class).findFirst().orElseThrow();

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}

	@GetMapping("/user/average-age")
	public double getAverageUserAge() {
		return service.getAverageUserAge();
	}

	@PostMapping("/user/subscribe")
	public void subscribe(String cardNumber) {
		bank.findBankCardByNumber(cardNumber)
			.ifPresentOrElse(service::subscribe, () -> {
				throw new NoSuchElementException("Bank card not found");
			});
	}
}
