package com.epam.engx.jmp.dto

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class SubscriptionSpec extends Specification {

	@Subject
	Subscription subscription

	def 'create Subscription with valid bankcard and startDate'() {
		given:
		def bankcard = "1234567890123456"
		def startDate = LocalDate.now()

		when:
		subscription = new Subscription(bankcard, startDate)

		then:
		with (subscription) {
			it.bankcard() == bankcard
			it.startDate() == startDate
		}
	}

	def 'create Subscription with null bankcard, expecting exception'() {
		when:
		subscription = new Subscription(null, LocalDate.now())

		then:
		def exception = thrown NullPointerException
		and:
		exception.message == "Bankcard cannot be null"
	}

	def 'create Subscription with null startDate, expecting exception'() {
		when:
		subscription = new Subscription("1234567890123456", null)

		then:
		def exception = thrown NullPointerException
		and:
		exception.message == "Start date cannot be null"
	}
}
