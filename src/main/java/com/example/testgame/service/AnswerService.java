package com.example.testgame.service;

import com.example.testgame.dto.AnswerStatusDto;
import com.example.testgame.entity.Answer;
import com.example.testgame.enums.Status;

import java.util.List;
import java.util.Map;

public interface AnswerService {

    Answer saveAnswer(Map<String, String> history);

    List<Answer> findAllUsersAnswers(Long userId);

    Integer getTotalAnswersByUser(Long userId);

    Integer getTotalPages(Integer totalElements, int limit );

    Integer getTotalAnswersByUserWithFilter(Long userId, Status status);

    List<Answer> findAnswersByUserWithTotalPage(Long userId, Integer page);

    List<Answer> findAnswersByUserWithTotalPageWithFilter(Long userId, Integer page, String status);

    List<AnswerStatusDto> getAllStatuses();

}
