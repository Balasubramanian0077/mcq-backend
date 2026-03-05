package com.mcq.quizapp.model; // or com.mcq.quizapp.dto

public class AnswerRequest {
    private long questionId;
    private String answer;

    public long getQuestionId() { return questionId; }
    public void setQuestionId(long questionId) { this.questionId = questionId; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}
