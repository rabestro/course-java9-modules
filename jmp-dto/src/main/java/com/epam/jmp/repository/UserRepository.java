package com.epam.jmp.repository;

import com.epam.jmp.entity.BankUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BankUser, Long> {
	Optional<BankUser> findByName(String name);
}
