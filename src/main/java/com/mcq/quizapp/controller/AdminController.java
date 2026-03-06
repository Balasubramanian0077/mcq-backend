package com.mcq.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mcq.quizapp.model.Admin;
import com.mcq.quizapp.model.Question;
import com.mcq.quizapp.model.UserResult;
import com.mcq.quizapp.repository.UserResultRepository;
import com.mcq.quizapp.service.AdminService;
import com.mcq.quizapp.service.QuestionService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private UserResultRepository userResultRepository;

    @PostMapping("/adminregister")
    public Admin register(@Valid @RequestBody Admin admin){
        return adminService.createAccount(admin);
    }

    @PostMapping("/adminlogin")
    public String login(@RequestBody Admin admin) {
        return adminService.login(admin.getEmail(), admin.getPassword());
    }

    @GetMapping("/questions/all")
    public List<Question> allQuestionsForAdmin() {
        return questionService.getAllQuestion();
    }
    
    @PostMapping("/add/questions")
    public Question add(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("/results/all")
    public List<UserResult> allResults() {
        return userResultRepository.findAll();
    }
}
