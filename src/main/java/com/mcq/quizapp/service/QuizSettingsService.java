package com.mcq.quizapp.service;

import org.springframework.stereotype.Service;

import com.mcq.quizapp.model.QuizSettings;
import com.mcq.quizapp.repository.QuizSettingsRepository;

@Service
public class QuizSettingsService {

    private final QuizSettingsRepository repo;

    public QuizSettingsService(QuizSettingsRepository repo) {
        this.repo = repo;
    }

    public QuizSettings getSettings() {
        return repo.findById(1L).orElseGet(() -> {
            QuizSettings s = new QuizSettings();
            s.setDurationMinutes(10); // default 10 min
            return repo.save(s);
        });
    }

    public QuizSettings setDuration(int minutes) {
        QuizSettings s = getSettings();
        s.setDurationMinutes(minutes);
        return repo.save(s);
    }
}