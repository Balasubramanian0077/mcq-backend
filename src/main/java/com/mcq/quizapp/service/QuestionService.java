package com.mcq.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcq.quizapp.model.Question;
import com.mcq.quizapp.model.QuestionDTO;
import com.mcq.quizapp.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // Admin add question
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    // Admin view all
    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    // User view all (WITHOUT correctAnswer)
    public List<QuestionDTO> getAllQuestionForUser() {
        List<Question> questions = questionRepository.findAll();
        return convertToDTOList(questions);
    }

    // ✅ NEW: User view by category (WITHOUT correctAnswer)
    public List<QuestionDTO> getQuestionsForUserByCategory(String category) {
        List<Question> questions = questionRepository.findByCategoryIgnoreCase(category);
        return convertToDTOList(questions);
    }

    // ✅ Helper: convert entity list -> dto list
    private List<QuestionDTO> convertToDTOList(List<Question> questions) {
        List<QuestionDTO> dtoList = new ArrayList<>();

        for (Question q : questions) {
            QuestionDTO dto = new QuestionDTO();
            dto.setId(q.getId());
            dto.setCategory(q.getCategory()); // ✅ NEW
            dto.setQuestionText(q.getQuestionText());
            dto.setOptionA(q.getOptionA());
            dto.setOptionB(q.getOptionB());
            dto.setOptionC(q.getOptionC());
            dto.setOptionD(q.getOptionD());
            dtoList.add(dto);
        }

        return dtoList;
    }
}