module jmp.application {
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.webmvc;

	requires jmp.dto;
	requires jmp.bank.api;
	requires jmp.service.api;
	requires jmp.cloud.bank.impl;
	requires jmp.cloud.service.impl;
    requires spring.web;

    uses com.epam.jmp.api.Bank;
	uses com.epam.jmp.service.api.Service;

	opens com.epam.jmp.app to spring.core;
	opens com.epam.jmp.app.controller to spring.core;
}
