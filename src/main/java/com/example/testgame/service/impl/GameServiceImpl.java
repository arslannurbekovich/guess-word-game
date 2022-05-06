package com.example.testgame.service.impl;

import com.example.testgame.entity.Question;
import com.example.testgame.service.GameService;
import com.example.testgame.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {

    private final QuestionService questionService;

    public GameServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questions = questionService.getAllQuestions();

        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(questions.size());
        return questions.get(index);
    }
}
