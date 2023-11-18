package com.epam.jmp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.epam.jmp.entity.User;

@DataJpaTest
class UserRepositoryTest {
	@Autowired
	UserRepository userRepository;
	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	@DisplayName("Find user by existing name")
	void findByName_existingName_returnsUser() {
		var user = new User();
		user.setName("John");
		user.setSurname("Doe");
		user.setBirthday(LocalDate.of(1990, 1, 1));
		entityManager.persist(user);
		entityManager.flush();

		var foundUser = userRepository.findByName("John");

		assertThat(foundUser)
			.isNotNull();

		assertThat(foundUser.get().getName())
			.isEqualTo("John");
	}

	@Test
	@DisplayName("Find user by non-existing name")
	void findByName_nonExistingName_returnsNull() {
		var user = userRepository.findByName("NonExistingName");

		assertThat(user).isEmpty();
	}

	@Test
	@DisplayName("Find user by null name")
	void findByName_nullName_throwsIllegalArgumentException() {
		var user = userRepository.findByName(null);

		assertThat(user).isEmpty();
	}

}
