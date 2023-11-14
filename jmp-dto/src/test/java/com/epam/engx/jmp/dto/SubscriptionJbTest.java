package com.epam.engx.jmp.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class SubscriptionJbTest {

    @DisplayName("Valid Subscription Test:")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("validSubscriptionProvider")
    void validSubscriptionTest(String description, String bankcard, LocalDate startDate) {
        var subscription = new Subscription(bankcard, startDate);

        assertThat(subscription)
            .withFailMessage("Expected a valid subscription object, but was invalid.")
            .satisfies(sub -> {
                assertThat(sub.bankcard()).isEqualTo(bankcard);
                assertThat(sub.startDate()).isEqualTo(startDate);
            });
    }

    @DisplayName("Equals Method Test:")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("equalsMethodDataProvider")
    void equalsMethodTest(String description, Subscription subscription1, Object subscription2, boolean expectedResult) {
        assertThat(subscription1.equals(subscription2))
            .withFailMessage("Expecting equalTo operation to return %s, but was different.", expectedResult)
            .isEqualTo(expectedResult);
    }

    @DisplayName("Negative Test: Null Bankcard")
	@ParameterizedTest(name = "[{index}] {0}")
	@NullSource
    void invalidBankcardTest(String bankcard) {
        assertThatThrownBy(() -> new Subscription(bankcard, LocalDate.now()))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Bankcard cannot be null");
    }

    @DisplayName("Negative Test: Null Start Date")
    @ParameterizedTest(name = "[{index}] {0}")
	@NullSource
    void invalidStartDateTest(LocalDate startDate) {
        assertThatThrownBy(() -> new Subscription("Valid Bankcard", startDate))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Start date cannot be null");
    }

	static Stream<Arguments> validSubscriptionProvider() {
		return Stream.of(
			Arguments.of("Valid Subscription", "1234567890", LocalDate.now())
		);
	}

	static Stream<Arguments> equalsMethodDataProvider() {
		var startDate = LocalDate.now();
		Subscription sub = new Subscription("1234567890", startDate);
		return Stream.of(
			Arguments.of("Equal Test: same subscription objects", sub, new Subscription("1234567890", startDate), true),
			Arguments.of("Equal Test: different subscription objects", sub, new Subscription("0987654321", startDate), false),
			Arguments.of("Equal Test: subscription object and null", sub, null, false),
			Arguments.of("Equal Test: subscription object and different class", sub, "String Object", false),
			Arguments.of("Equal Test: subscription object with itself", sub, sub, true)
		);
	}
}
