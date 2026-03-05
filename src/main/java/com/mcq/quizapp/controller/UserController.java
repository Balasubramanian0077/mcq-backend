package com.mcq.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mcq.quizapp.model.QuestionDTO;
import com.mcq.quizapp.model.User;
import com.mcq.quizapp.service.QuestionService;
import com.mcq.quizapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    // user register
    @PostMapping("/userregister")
    public User createAccount(@RequestBody User user) {
        return userService.createAccount(user);
    }

    // user login
    @PostMapping("/userlogin")
    public ResponseEntity<?> loginUser(@RequestBody User user) {

        User existingUser = userService.login(user.getEmail(), user.getPassword());

        if (existingUser == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Email or Password");
        }

        return ResponseEntity.ok(existingUser);
    }

    // user view all questions
    @GetMapping("/userviewallquestion")
    public List<QuestionDTO> userView() {
        return questionService.getAllQuestionForUser();
    }

    // ✅ NEW: user view by category
    @GetMapping("/questions/category/{category}")
    public List<QuestionDTO> userViewByCategory(@PathVariable String category) {
        return questionService.getQuestionsForUserByCategory(category);
    }
}