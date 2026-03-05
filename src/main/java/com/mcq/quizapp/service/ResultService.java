package com.mcq.quizapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcq.quizapp.model.AnswerRequest;
import com.mcq.quizapp.model.Question;
import com.mcq.quizapp.model.UserResult;
import com.mcq.quizapp.repository.QuestionRepository;
import com.mcq.quizapp.repository.UserResultRepository;

@Service
public class ResultService {

    @Autowired
    private UserResultRepository userResultRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // ✅ SAVE ONE MODULE RESULT
    public String calculateAndSaveResult(String userEmail, String category, List<AnswerRequest> answers) {

        // ✅ block multiple attempts PER CATEGORY
        if (userResultRepository.existsByUserEmailAndCategory(userEmail, category)) {
            return "You already attended the " + category + " test.";
        }

        int score = 0;

        for (AnswerRequest a : answers) {
            Question q = questionRepository.findById(a.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            if (a.getAnswer() != null &&
                a.getAnswer().equalsIgnoreCase(q.getCorrectAnswer())) {
                score++;
            }
        }

        int total = answers.size();

        double percentage = (total > 0) ? (score * 100.0) / total : 0.0;
        String status = (percentage >= 50.0) ? "PASS" : "FAIL";

        String grade;
        if (percentage >= 90) grade = "A";
        else if (percentage >= 75) grade = "B";
        else if (percentage >= 50) grade = "C";
        else grade = "D";

        UserResult result = new UserResult();
        result.setUserEmail(userEmail);
        result.setCategory(category); // ✅ IMPORTANT
        result.setScore(score);
        result.setTotalQuestions(total);
        result.setPercentage(percentage);
        result.setStatus(status);
        result.setGrade(grade);
        result.setSubmittedAt(LocalDateTime.now());

        userResultRepository.save(result);

        return category + " Result => " +
                "Score: " + score + "/" + total +
                " | Percentage: " + String.format("%.2f", percentage) + "%" +
                " | Status: " + status +
                " | Grade: " + grade;
    }

    // ✅ FINAL RESULT (Aptitude + Technical + Verbal)
    public String getFinalResult(String userEmail) {

        List<UserResult> results = userResultRepository.findByUserEmail(userEmail);

        if (results == null || results.isEmpty()) {
            return "No results found for " + userEmail;
        }

        int totalScore = 0;
        int totalQuestions = 0;

        for (UserResult r : results) {
            totalScore += r.getScore();
            totalQuestions += r.getTotalQuestions();
        }

        double percentage = (totalQuestions > 0) ? (totalScore * 100.0) / totalQuestions : 0.0;
        String status = (percentage >= 50.0) ? "PASS" : "FAIL";

        String grade;
        if (percentage >= 90) grade = "A";
        else if (percentage >= 75) grade = "B";
        else if (percentage >= 50) grade = "C";
        else grade = "D";

        return "FINAL Result => " +
                "Total Score: " + totalScore + "/" + totalQuestions +
                " | Percentage: " + String.format("%.2f", percentage) + "%" +
                " | Status: " + status +
                " | Grade: " + grade;
    }
}