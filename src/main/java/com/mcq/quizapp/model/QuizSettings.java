package com.mcq.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class QuizSettings {

    @Id
    private Long id = 1L; // always one row

    private int durationMinutes; // ex: 10

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }
}