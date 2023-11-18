package com.epam.jmp.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BankUserRepositoryTest {
	@Autowired
	UserRepository userRepository;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@DisplayName("Find user by existing name")
	@ParameterizedTest
	@Sql("/users.sql")
	@CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
	void findByName_existingName_returnsUser(String name, String surname, LocalDate birthday) {
		var foundUser = userRepository
			.findByName(name);

		assertThat(foundUser)
			.isPresent().get()
			.hasFieldOrPropertyWithValue("name", name)
			.hasFieldOrPropertyWithValue("surname", surname)
			.hasFieldOrPropertyWithValue("birthday", birthday);
	}

	@Test
	@DisplayName("Find user by non-existing name")
	void findByName_nonExistingName_returnsNull() {
		var user = userRepository.findByName("NonExistingName");

		assertThat(user).isEmpty();
	}

	@Test
	@DisplayName("Find user by null name")
	void findByName_nullName_throwsIllegalArgumentException() {
		var user = userRepository.findByName(null);

		assertThat(user).isEmpty();
	}
}
