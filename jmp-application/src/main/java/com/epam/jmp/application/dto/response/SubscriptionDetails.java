package com.epam.jmp.application.dto.response;

import java.time.LocalDate;

public record SubscriptionDetails(LocalDate startDate, String bankCardNumber, String user) {
	public SubscriptionDetails(com.epam.jmp.dto.Subscription subscription) {
		this(
			subscription.startDate(),
			subscription.bankcard().number(),
			subscription.bankcard().user().name() + " "
			+ subscription.bankcard().user().surname());
	}
}
