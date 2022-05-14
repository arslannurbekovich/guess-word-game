package com.example.testgame.service;

import com.example.testgame.entity.Question;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestions();

    Question saveQuestion(Question question);

    Question getQuestionById(Long id);

    Question updateQuestion(Question question,Long id);

    void deleteQuestionById(Long id);

    Question findQuestionByAnswer(String answer);

    Page<Question> getQuestionPagination(Integer currentPage, Integer size);

    Page<Question> getQuestionPaginationWithSearch(Integer currentPage, Integer size, String keyword);
}
