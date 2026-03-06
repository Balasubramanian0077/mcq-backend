package com.mcq.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mcq.quizapp.model.AnswerRequest;
import com.mcq.quizapp.service.ResultService;

@RestController

@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/submit/{email}/{category}")
    public String submitResult(
            @PathVariable String email,
            @PathVariable String category,
            @RequestBody List<AnswerRequest> answers) {

        return resultService.calculateAndSaveResult(email, category, answers);
    }

    // ✅ FINAL RESULT API
    @GetMapping("/final/{email}")
    public String finalResult(@PathVariable String email) {
        return resultService.getFinalResult(email);
    }
}