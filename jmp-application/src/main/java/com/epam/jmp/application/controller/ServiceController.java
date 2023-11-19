package com.epam.jmp.application.controller;

import com.epam.jmp.dto.User;
import com.epam.jmp.service.api.Service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ServiceLoader;

@RestController
@RequestMapping("/api")
public class ServiceController {
	private final Service service = ServiceLoader.load(Service.class).findFirst().orElseThrow();

	@GetMapping("/user")
	@ResponseBody
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
}
