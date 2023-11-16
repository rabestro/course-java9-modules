package com.epam.engx.jmp.cloud.bank.cardnumber


import spock.lang.Specification

class LuhnValidatorSpec extends Specification {

	def "Test with a known valid card number: #cardNumber"() {
		given:
		def luhnCheck = new LuhnValidator()

		when:
		def isValid = luhnCheck.test(cardNumber)

		then:
		isValid

		where:
		cardNumber << [
			"4532015112830366",
			"6011514433546201",
			"6771549495586802",
			"4556737586899855",
			"6011000991300009"
		]
	}

	def "Test with a known invalid card number: #cardNumber"() {
		given:
		def luhnCheck = new LuhnValidator()

		when:
		def isValid = luhnCheck.test(cardNumber)

		then:
		!isValid

		where:
		cardNumber << [
			"4532015112830367",
			"6011514433546202",
			"6771549495586803",
			"4716343014615113",
			"6011000991300010"
		]
	}

	def "Test with a null card number"() {
		given:
		def luhnCheck = new LuhnValidator()

		when:
		def isValid = luhnCheck.test(null)

		then:
		!isValid
	}

	def "Test with an empty card number"() {
		given:
		def luhnCheck = new LuhnValidator()

		when:
		def isValid = luhnCheck.test("")

		then:
		!isValid
	}

	def "Test with a card number containing non-numeric characters"() {
		given:
		def luhnCheck = new LuhnValidator()

		when:
		def isValid = luhnCheck.test("4532a15112830366")

		then:
		!isValid
	}
}
