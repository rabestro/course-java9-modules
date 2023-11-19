package com.epam.jmp.cloud.bank

import com.epam.jmp.dto.BankCardType
import com.epam.jmp.dto.CreditBankCard
import com.epam.jmp.dto.DebitBankCard
import com.epam.jmp.dto.User
import spock.lang.*

import java.time.LocalDate

@Title('Bank operations')
@Narrative('''
As a bank
I want to create bank cards
So that users can use them
''')
class BankImplTest extends Specification {
	@Subject
	def bank = new BankImpl()

	@Shared
	def john = new User("John", "Doe", LocalDate.of(1956, 3, 11))
	@Shared
	def bill = new User("Bill", "Brown", LocalDate.of(1942, 9, 23))

	def 'create bank card'() {
		when:
		def card = bank.createBankCard(user, cardType)

		then:
		card.class == expectedType
		card.user() == user

		where:
		user | cardType            | expectedType
		john | BankCardType.CREDIT | CreditBankCard
		bill | BankCardType.DEBIT  | DebitBankCard
	}

	def 'find bank card by existing number'() {
		given:
		def cardOne = bank.createBankCard(john, BankCardType.CREDIT)
		def cardTwo = bank.createBankCard(bill, BankCardType.DEBIT)

		when:
		def card = bank.findBankCardByNumber cardOne.number()

		then:
		card.isPresent()

		and:
		with(card.get()) {
			it instanceof CreditBankCard
			it == cardOne
		}

		when:
		card = bank.findBankCardByNumber cardTwo.number()

		then:
		card.isPresent()
		card.get() instanceof DebitBankCard
		card.get() == cardTwo
	}

	def 'find bank card by non existing number'() {
		when:
		def card = bank.findBankCardByNumber nonExistingNumber

		then:
		card.isEmpty()

		where:
		nonExistingNumber = "1234567890123456"
	}
}
