package com.example.QuizApp.service;

import com.example.QuizApp.entity.Question;
import com.example.QuizApp.entity.QuizSession;
import com.example.QuizApp.repository.QuestionRepository;
import com.example.QuizApp.repository.QuizSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class QuizService {

    private final QuestionRepository questionRepository;
    private final QuizSessionRepository quizSessionRepository;

    @Autowired
    public QuizService(QuestionRepository questionRepository, QuizSessionRepository quizSessionRepository) {
        this.questionRepository = questionRepository;
        this.quizSessionRepository = quizSessionRepository;
    }

    public QuizSession startNewSession(String username) {
        QuizSession session = QuizSession.builder()
                .sessionId(UUID.randomUUID())
                .username(username)
                .questionsSeen(new HashSet<>())
                .correctAns(0)
                .incorrectAns(0)
                .build();
        return quizSessionRepository.save(session);
    }



    public Question getRandomQuestion(UUID sessionId) {
        QuizSession session = quizSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        List<Question> allQuestions = questionRepository.findAll();
        Question randomQuestion = allQuestions.get(new Random().nextInt(allQuestions.size()));
        session.getQuestionsSeen().add(randomQuestion.getId());
        //session.getQuestionsSeen().add(randomQuestion.getId());
        quizSessionRepository.save(session);

        return randomQuestion;
    }

    public boolean submitAnswer(UUID sessionId, Long questionId, String answer) {
        QuizSession session = quizSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(answer);

        if (isCorrect) {
            session.setCorrectAns(session.getCorrectAns() + 1);
        } else {
            session.setIncorrectAns(session.getIncorrectAns() + 1);
        }

        quizSessionRepository.save(session);
        return isCorrect;
    }

    public QuizSession getSessionSummary(UUID sessionId) {
        return quizSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
    }

}
