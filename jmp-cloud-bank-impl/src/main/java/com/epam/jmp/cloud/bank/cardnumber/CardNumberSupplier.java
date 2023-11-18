package com.epam.jmp.cloud.bank.cardnumber;

import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

public final class CardNumberSupplier implements Supplier<String> {
	private static final int CARD_NUMBER_LENGTH = 16;
	private static final Random RANDOM = new SecureRandom();

	@Override
	public String get() {
		StringBuilder cardNumberBuilder = new StringBuilder(CARD_NUMBER_LENGTH - 1);
		for (int i = 0; i < CARD_NUMBER_LENGTH - 1; i++) {
			cardNumberBuilder.append(RANDOM.nextInt(10));
		}

		String cardNumberWithMissingCheckDigit = cardNumberBuilder.toString();
		int checkDigit = calculateLuhnCheckDigit(cardNumberWithMissingCheckDigit);
		cardNumberBuilder.append(checkDigit);

		return cardNumberBuilder.toString();
	}

	int calculateLuhnCheckDigit(String cardNumberWithMissingCheckDigit) {
		int sum = 0;
		boolean alternate = true;
		for (int i = cardNumberWithMissingCheckDigit.length() - 1; i >= 0; i--) {
			int digit = Character.getNumericValue(cardNumberWithMissingCheckDigit.charAt(i));
			if (alternate) {
				digit *= 2;
				if (digit > 9) {
					digit -= 9;
				}
			}
			sum += digit;
			alternate = !alternate;
		}

		return 10 - (sum % 10);
	}
}
