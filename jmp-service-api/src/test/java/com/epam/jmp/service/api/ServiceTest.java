package com.epam.jmp.service.api;

import com.epam.jmp.dto.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static com.epam.jmp.service.api.Service.isPayableUser;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Service isPayableUser Tests")
class ServiceIsPayableUserTests {

	@ParameterizedTest(name = "User is {0}, expected result: {1}")
	@MethodSource("provideUserAndExpectedResult")
	void testIsPayableUser(User user, boolean expectedResult) {
		var result = isPayableUser(user);
		assertThat(result).isEqualTo(expectedResult);
	}

	private static Stream<Arguments> provideUserAndExpectedResult() {
		return Stream.of(
			Arguments.of(new User("John", "Doe",
				LocalDate.now().minusYears(17).plusDays(1)), false),
			Arguments.of(new User("Jane", "Doe",
				LocalDate.now().minusYears(18)), false),
			Arguments.of(new User("Mike", "Whale",
				LocalDate.now().minusYears(18).minusDays(1)), true),
			Arguments.of(new User("Alice", "Smith",
				LocalDate.now().minusYears(19)), true)
		);
	}
}
