package com.mcq.quizapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcq.quizapp.model.Admin;


public interface AdminRepository extends JpaRepository<Admin,Long> {

	Optional<Admin> findByEmail(String email);

}
