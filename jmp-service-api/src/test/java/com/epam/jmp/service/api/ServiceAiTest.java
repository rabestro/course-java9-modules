package com.epam.jmp.service.api;

import com.epam.jmp.dto.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceAiTest {
    private static final Clock FIXED_CLOCK = Clock.fixed(Instant.parse("2023-01-01T00:00:00.00Z"), ZoneId.of("UTC"));

    static Stream<Arguments> userTestData() {
        return Stream.of(
            Arguments.of("User is exactly 18 years old.", LocalDate.of(2005, 1, 1), true),
            Arguments.of("User is younger than 18 years old.", LocalDate.of(2006, 1, 2), false),
            Arguments.of("User is older than 18 years old.", LocalDate.of(2003, 12, 31), true)
        );
    }

    @DisplayName("isPayableUser test:")
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("userTestData")
    void isPayableUserTest(String description, LocalDate birthday, boolean expectedOutput) {
        var user = new User("John", "Doe", birthday);
        var predicate = new LegalAgePredicate(FIXED_CLOCK);
        assertThat(predicate.test(user))
			.as("User '%s %s' with birthdate '%s' should return: %b", user.name(), user.surname(), birthday, expectedOutput)
			.isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("isPayableUser should return false when User is null")
    void isPayableUser_ShouldReturnFalse_WhenUserIsNull() {
        var predicate = new LegalAgePredicate(FIXED_CLOCK);
        assertThat(predicate.test(null))
			.as("For NULL user should return: false")
			.isFalse();
    }

    @Test
    @DisplayName("isPayableUser should return false when User birthday is null")
    void isPayableUser_ShouldReturnFalse_WhenUserBirthdayIsNull() {
        var user = new User("John", "Doe", null);
        var predicate = new LegalAgePredicate(FIXED_CLOCK);
        assertThat(predicate.test(user))
			.as("User '%s %s' with NULL birthdate should return: false", user.name(), user.surname())
			.isFalse();
    }
}
