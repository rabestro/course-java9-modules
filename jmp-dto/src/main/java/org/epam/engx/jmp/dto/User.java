package org.epam.engx.jmp.dto;

import java.time.LocalDate;
import java.util.Objects;

public class User {
	private final String name;
	private final String surname;
	private final LocalDate birthday;

	public User(String name, String surname, LocalDate birthday) {
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(birthday, user.birthday);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, surname, birthday);
	}

	@Override
	public String toString() {
		return "User{" +
			   "name='" + name + '\'' +
			   ", surname='" + surname + '\'' +
			   ", birthday=" + birthday +
			   '}';
	}
}
