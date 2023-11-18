package com.epam.jmp.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;

@Entity
public class BankUser extends AbstractPersistable<Long> {
	@NotBlank
	private String name;
	@NotBlank
	private String surname;
	@Past
	@NotNull
	private LocalDate birthday;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
}
