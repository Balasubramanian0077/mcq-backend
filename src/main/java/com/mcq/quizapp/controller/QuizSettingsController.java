package com.mcq.quizapp.controller;

import org.springframework.web.bind.annotation.*;

import com.mcq.quizapp.model.QuizSettings;
import com.mcq.quizapp.service.QuizSettingsService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/quiz")
public class QuizSettingsController {

    private final QuizSettingsService service;

    public QuizSettingsController(QuizSettingsService service) {
        this.service = service;
    }

    // ✅ User side: get timer duration
    @GetMapping("/settings")
    public QuizSettings getSettings() {
        return service.getSettings();
    }

    // ✅ Admin side: update duration
    @PostMapping("/settings/{minutes}")
    public QuizSettings updateDuration(@PathVariable int minutes) {
        if (minutes < 1) minutes = 1;
        if (minutes > 180) minutes = 180; // max 3 hours safety
        return service.setDuration(minutes);
    }
}