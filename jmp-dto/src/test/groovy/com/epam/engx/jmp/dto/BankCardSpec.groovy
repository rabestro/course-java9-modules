package com.epam.engx.jmp.dto

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class BankCardSpec extends Specification {

	@Subject
	BankCard bankCard

	def "create BankCard with valid number and user"() {
		given:
		def number = "1234567890123456"
		def user = new User("John", "Doe", LocalDate.of(1990, 1, 1))

		when:
		bankCard = new BankCard(number, user)

		then:
		bankCard.number() == number
		bankCard.user() == user
	}

	def "create BankCard with null number, expecting exception"() {
		given:
		User user = new User("John", "Doe", LocalDate.of(1990, 1, 1))

		when:
		bankCard = new BankCard(null, user)

		then:
		def exception = thrown NullPointerException
		and:
		exception.message == "Number cannot be null"
	}

	def "create BankCard with null user, expecting exception"() {
		when:
		bankCard = new BankCard("1234567890123456", null)

		then:
		def exception = thrown NullPointerException
		and:
		exception.message == "User cannot be null"
	}

	def "equals() method for two BankCards with the same number and user"() {
		given:
		String number = "1234567890123456"
		User user = new User("John", "Doe", LocalDate.of(1990, 1, 1))
		BankCard bankCard1 = new BankCard(number, user)
		BankCard bankCard2 = new BankCard(number, user)

		expect:
		bankCard1 == bankCard2
	}

	def "equals() method for two BankCards with different numbers"() {
		given:
		User user = new User("John", "Doe", LocalDate.of(1990, 1, 1))
		BankCard bankCard1 = new BankCard("1234567890123456", user)
		BankCard bankCard2 = new BankCard("2345678901234567", user)

		expect:
		bankCard1 != bankCard2
	}

	def "equals() method for two BankCards with different users"() {
		given:
		String number = "1234567890123456"
		User user1 = new User("John", "Doe", LocalDate.of(1990, 1, 1))
		User user2 = new User("Jane", "Doe", LocalDate.of(1990, 1, 1))
		BankCard bankCard1 = new BankCard(number, user1)
		BankCard bankCard2 = new BankCard(number, user2)

		expect:
		bankCard1 != bankCard2
	}

	def "hashCode() method for two BankCards with the same number and user"() {
		given:
		String number = "1234567890123456"
		User user = new User("John", "Doe", LocalDate.of(1990, 1, 1))
		BankCard bankCard1 = new BankCard(number, user)
		BankCard bankCard2 = new BankCard(number, user)

		expect:
		bankCard1.hashCode() == bankCard2.hashCode()
	}

	def "toString() method for a BankCard"() {
		given:
		def number = "1234567890123456"
		def user = new User("John", "Doe", LocalDate.of(1990, 1, 1))

		when:
		bankCard = new BankCard(number, user)

		then:
		bankCard.toString() == "BankCard{number='${number}', user=${user}}"
	}
}
