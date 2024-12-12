package com.example.QuizApp.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@With
public class QuizSession {

    @Id
    private UUID sessionId;

    private String username;

    @ElementCollection
    private HashSet<Long> questionsSeen;

    private int correctAns;
    private int incorrectAns;

    public QuizSession(UUID sessionId, String username, HashSet<Long> questionsSeen, int correctAns, int incorrectAns) {
        this.sessionId = sessionId;
        this.username = username;
        this.questionsSeen = questionsSeen;
        this.correctAns = correctAns;
        this.incorrectAns = incorrectAns;
    }
}
