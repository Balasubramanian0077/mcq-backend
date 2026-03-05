package com.mcq.quizapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcq.quizapp.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findByEmail(String email);
}
