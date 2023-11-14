package com.epam.engx.jmp.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserMcTest {
    private User user;
    private User anotherUser;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe", LocalDate.of(1990, 1, 1));
        anotherUser = new User("Jane", "Doe", LocalDate.of(1995, 1, 1));
    }

    @Test
    void testGetNameWhenNameIsSetThenReturnName() {
        assertEquals("John", user.getName());
    }

    @Test
    void testGetNameWhenNameIsNullThenReturnNull() {
        User userWithNullName = new User(null, "Doe", LocalDate.of(1990, 1, 1));
        assertNull(userWithNullName.getName());
    }

    @Test
    void testGetSurnameWhenSurnameIsSetThenReturnSurname() {
        assertEquals("Doe", user.getSurname());
    }

    @Test
    void testGetSurnameWhenSurnameIsNullThenReturnNull() {
        User userWithNullSurname = new User("John", null, LocalDate.of(1990, 1, 1));
        assertNull(userWithNullSurname.getSurname());
    }

    @Test
    void testGetBirthdayWhenBirthdayIsSetThenReturnBirthday() {
        assertEquals(LocalDate.of(1990, 1, 1), user.getBirthday());
    }

    @Test
    void testGetBirthdayWhenBirthdayIsNullThenReturnNull() {
        User userWithNullBirthday = new User("John", "Doe", null);
        assertNull(userWithNullBirthday.getBirthday());
    }

    @Test
    void testEqualsWhenObjectsAreEqualThenReturnTrue() {
        User sameUser = new User("John", "Doe", LocalDate.of(1990, 1, 1));
        assertEquals(user, sameUser);
    }

    @Test
    void testEqualsWhenObjectsAreNotEqualThenReturnFalse() {
        assertNotEquals(user, anotherUser);
    }

    @Test
    void testHashCodeWhenObjectsAreEqualThenReturnEqualHashCodes() {
        User sameUser = new User("John", "Doe", LocalDate.of(1990, 1, 1));
        assertEquals(user.hashCode(), sameUser.hashCode());
    }

    @Test
    void testHashCodeWhenObjectsAreNotEqualThenReturnDifferentHashCodes() {
        assertNotEquals(user.hashCode(), anotherUser.hashCode());
    }

    @Test
    void testToStringThenReturnStringWithUserDetails() {
        String expectedString = "User{name='John', surname='Doe', birthday=1990-01-01}";
        assertEquals(expectedString, user.toString());
    }
}
