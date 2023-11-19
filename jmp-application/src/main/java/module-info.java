module jmp.application {
	// Spring
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.webmvc;
	requires spring.core;
	requires spring.context;
	requires spring.beans;
	requires spring.web;

	// Project
	requires jmp.dto;
	requires jmp.bank.api;
	requires jmp.service.api;
	requires jmp.cloud.bank.impl;
	requires jmp.cloud.service.impl;

    uses com.epam.jmp.api.Bank;
	uses com.epam.jmp.service.api.Service;

	opens com.epam.jmp.application;
	opens com.epam.jmp.application.controller;
}
