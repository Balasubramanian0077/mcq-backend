package com.mcq.quizapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcq.quizapp.model.Admin;
import com.mcq.quizapp.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
//regisster process
	public Admin createAccount(Admin admin) {
		return adminRepository.save(admin);
	}
//Login process
	public String login(String email,String password) {
		
//check email
		Optional<Admin>adminOpt=adminRepository.findByEmail(email);
		if(adminOpt.isEmpty()) {
			return "Email not found";
		}
	//check password	
		Admin admin=adminOpt.get();
		
		if(!admin.getPassword().equals(password)) {
			return "Invalid password";
		}
	//the data is correct
		return "Admin Login success";
	}
}
