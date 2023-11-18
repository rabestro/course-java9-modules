package com.epam.jmp.cloud.bank


import com.epam.jmp.dto.BankCardType
import com.epam.jmp.dto.CreditBankCard
import com.epam.jmp.dto.DebitBankCard
import com.epam.jmp.dto.User
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class BankImplSpec extends Specification {

	@Subject
	BankImpl bankCloud = new BankImpl()

	def "Test creating a credit bank card for a valid user"() {
		given:
		def user = new User("John", "Doe", LocalDate.of(1990, 1, 1))

		when:
		def card = bankCloud.createBankCard(user, BankCardType.CREDIT)

		then:
		with(card) {
			it != null
			it instanceof CreditBankCard
			it.user() == user
			it.number() != null
		}
	}

	def "Test creating a debit bank card for a valid user"() {
		given:
		def user = new User("Jane", "Doe", LocalDate.of(1992, 2, 2))

		when:
		def card = bankCloud.createBankCard(user, BankCardType.DEBIT)

		then:
		card != null
		card instanceof DebitBankCard
		card.user() == user
		card.number() != null
	}

	def "Test creating a bank card with a null user"() {
		when:
		bankCloud.createBankCard(null, BankCardType.CREDIT)

		then:
		def exception = thrown NullPointerException
		exception.message == "User cannot be null"
	}

	def "Test creating a bank card with a null bank card type"() {
		given:
		User user = new User("Alice", "Smith", LocalDate.of(1994, 3, 3))

		when:
		bankCloud.createBankCard(user, null)

		then:
		def exception = thrown NullPointerException
		exception.message == "Bank card type cannot be null"
	}
}
