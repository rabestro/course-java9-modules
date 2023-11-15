package com.epam.engx.jmp.dto

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class UserSpec extends Specification {

	@Subject
	User user

	def "create User with valid name, surname, and birthday"() {
		given:
		def name = "John"
		def surname = "Doe"
		def birthday = LocalDate.of(1990, 1, 1)

		when:
		user = new User(name, surname, birthday)

		then:
		with(user) {
			it.name() == name
			it.surname() == surname
			it.birthday() == birthday
		}
	}

	def "create User with null name, expecting exception"() {
		when:
		user = new User(null, "Doe", LocalDate.of(1990, 1, 1))

		then:
		def exception = thrown NullPointerException
		and:
		exception.message == "Name cannot be null"
	}

	def "create User with null surname, expecting exception"() {
		when:
		user = new User("John", null, LocalDate.of(1990, 1, 1))

		then:
		def exception = thrown NullPointerException
		and:
		exception.message == "Surname cannot be null"
	}

	def "create User with null birthday, expecting exception"() {
		when:
		user = new User("John", "Doe", null)

		then:
		def exception = thrown NullPointerException
		and:
		exception.message == "Birthday cannot be null"
	}
}
