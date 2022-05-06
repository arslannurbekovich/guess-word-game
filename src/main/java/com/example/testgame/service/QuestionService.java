package com.example.testgame.service;

import com.example.testgame.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestions();

    Question saveQuestion(Question question);

    Question getQuestionById(Long id);

    Question updateQuestion(Question question);

    void deleteQuestionById(Long id);

    Question findQuestionByAnswer(String answer);
}
