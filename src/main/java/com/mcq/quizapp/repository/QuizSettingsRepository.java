package com.mcq.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mcq.quizapp.model.QuizSettings;

public interface QuizSettingsRepository extends JpaRepository<QuizSettings, Long> {
}