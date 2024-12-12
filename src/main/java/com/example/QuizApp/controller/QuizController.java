package com.example.QuizApp.controller;

import com.example.QuizApp.entity.QuizSession;
import com.example.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/start")
    public ResponseEntity<QuizSession> startNewSession(@RequestParam String username) {
        return ResponseEntity.ok(quizService.startNewSession(username));
    }


    @PostMapping("/submit")
    public ResponseEntity<String> submitAnswer(@RequestParam UUID sessionId,
                                               @RequestParam Long questionId,
                                               @RequestParam String answer) {
        boolean isCorrect = quizService.submitAnswer(sessionId, questionId, answer);
        return ResponseEntity.ok(isCorrect ? "Correct" : "Incorrect");
    }

    @GetMapping("/summary")
    public ResponseEntity<QuizSession> getSessionSummary(@RequestParam UUID sessionId) {
        return ResponseEntity.ok(quizService.getSessionSummary(sessionId));
    }
}
