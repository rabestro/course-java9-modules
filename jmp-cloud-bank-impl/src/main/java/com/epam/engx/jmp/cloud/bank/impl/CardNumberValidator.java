package com.epam.engx.jmp.cloud.bank.impl;

import java.util.function.Predicate;

/**
 * A class that provides Card Number validation functionality.
 *
 * <p>
 * This class implements the {@link Predicate} interface, allowing it to be used for testing a card number string.
 * The card number should not be null, consist of only digits (0-9), and follow the Luhn algorithm for validation.
 * </p>
 */
public final class CardNumberValidator implements Predicate<String> {

	@Override
	public boolean test(String cardNumber) {
		if (cardNumber == null) {
			return false;
		}
		var number = cardNumber.replace(" ", "");
		if (!number.matches("\\d{2,19}")) {
			return false;
		}

		var sum = 0;
		var isEven = number.length() % 2 == 0;
		for (var symbol : number.toCharArray()) {
			var digit = Character.getNumericValue(symbol);
			if (isEven) {
				digit *= 2;
				if (digit > 9) {
					digit -= 9;
				}
			}
			sum += digit;
			isEven = !isEven;
		}
		return sum % 10 == 0;
	}
}
