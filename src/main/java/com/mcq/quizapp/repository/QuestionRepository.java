package com.mcq.quizapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mcq.quizapp.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findById(long id);

    // ✅ NEW: category filter
    List<Question> findByCategoryIgnoreCase(String category);
}