package com.epam.engx.jmp.cloud.bank.impl


import spock.lang.Specification

class LuhnCardNumberSupplierSpec extends Specification {

	def "Generated card number should not be null"() {
		given:
		def cardNumberSupplier = new LuhnCardNumberSupplier()

		when:
		def cardNumber = cardNumberSupplier.get()

		then:
		cardNumber != null
	}

	def "Generated card number should have correct length"() {
		given:
		def cardNumberSupplier = new LuhnCardNumberSupplier()

		when:
		def cardNumber = cardNumberSupplier.get()

		then:
		cardNumber.length() == 16
	}

	def "Generated card number should pass Luhn algorithm check"() {
		given:
		def cardNumberSupplier = new LuhnCardNumberSupplier()
		def luhnCheck = new CardNumberValidator()

		when:
		def cardNumber = cardNumberSupplier.get()

		then:
		luhnCheck.test(cardNumber)
	}

	def "Test calculateLuhnCheckDigit with card number #cardNumberWithoutCheckDigit and check digit #expectedCheckDigit"() {
		given:
		def luhnCardNumberSupplier = new LuhnCardNumberSupplier()

		when:
		def checkDigit = luhnCardNumberSupplier.calculateLuhnCheckDigit(cardNumberWithoutCheckDigit)

		then:
		checkDigit == expectedCheckDigit

		where:
		cardNumberWithoutCheckDigit || expectedCheckDigit
		"453201511283036"           || 6
		"000000000000000"           || 10
		"999999999999999"           || 5
		"378282246310005"           || 2
		"601111111111117"           || 4
	}
}
