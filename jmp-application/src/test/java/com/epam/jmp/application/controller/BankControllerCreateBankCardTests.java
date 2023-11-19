package com.epam.jmp.application.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("BankController createBankCard Tests")
class BankControllerCreateBankCardTests {

	@Autowired
	private MockMvc mockMvc;

	private static Stream<Arguments> provideRequestAndExpectedStatus() {
		var validUser = new User("John", "Doe", LocalDate.now().minusYears(25));
		return Stream.of(
			Arguments.of(null, HttpStatus.BAD_REQUEST),
			Arguments.of(new BankCardRequest(null, BankCardType.CREDIT), HttpStatus.BAD_REQUEST),
			Arguments.of(new BankCardRequest(validUser, null), HttpStatus.BAD_REQUEST),
			Arguments.of(new BankCardRequest(validUser, BankCardType.CREDIT), HttpStatus.OK),
			Arguments.of(new BankCardRequest(validUser, BankCardType.DEBIT), HttpStatus.OK)
		);
	}

	@ParameterizedTest(name = "Request: {0}, expected status: {1}")
	@MethodSource("provideRequestAndExpectedStatus")
	void testCreateBankCard(BankCardRequest request, HttpStatus expectedStatus) throws Exception {
		var jsonRequest = new JsonMapper().writeValueAsString(request);

		mockMvc.perform(post("/api/createCard")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
			.andExpect(status().is(expectedStatus.value()))
			.andExpect(jsonPath("$.number").exists());
	}

	record BankCardRequest(User user, BankCardType type) {
	}
}
