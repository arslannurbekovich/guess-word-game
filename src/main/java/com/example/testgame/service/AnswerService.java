package com.example.testgame.service;

import com.example.testgame.entity.Answer;

import java.util.List;
import java.util.Map;

public interface AnswerService {

    Answer saveAnswer(Map<String, String> history);

    List<Answer> findAllUsersAnswers(Long userId);

    Integer getTotalAnswersByUser(Long userId);

    List<Answer> findAnswersByUserWithTotalPage(Long userId, Integer page);

}
