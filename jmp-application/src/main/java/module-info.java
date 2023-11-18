module jmp.application {
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.webmvc;

	requires jmp.bank.api;
	requires jmp.dto;

	opens com.epam.jmp.app to spring.core;
    uses com.epam.jmp.api.Bank;
}
