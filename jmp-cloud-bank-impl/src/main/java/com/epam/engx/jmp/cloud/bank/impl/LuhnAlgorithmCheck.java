package com.epam.engx.jmp.cloud.bank.impl;

import java.util.function.Predicate;

public class LuhnAlgorithmCheck implements Predicate<String> {

	@Override
	public boolean test(String cardNumber) {
		if (cardNumber == null || cardNumber.isEmpty()) {
			return false;
		}

		int sum = 0;
		boolean alternate = false;
		for (int i = cardNumber.length() - 1; i >= 0; i--) {
			int digit = Character.getNumericValue(cardNumber.charAt(i));
			if (alternate) {
				digit *= 2;
				if (digit > 9) {
					digit -= 9;
				}
			}
			sum += digit;
			alternate = !alternate;
		}

		return sum % 10 == 0;
	}
}
