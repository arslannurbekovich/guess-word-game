package com.example.testgame.service.impl;

import com.example.testgame.dao.QuestionRepository;
import com.example.testgame.entity.Question;
import com.example.testgame.exceptions.NotFoundException;
import com.example.testgame.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question saveQuestion(Question question) {

        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(()-> new NotFoundException("Вопрос не найдено!"));
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Question findQuestionByAnswer(String answer) {
        return questionRepository.getQuestionByAnswer(answer);
    }

    @Override
    public Page<Question> getQuestionPagination(Integer currentPage, Integer size) {

        Pageable pageable = PageRequest.of(currentPage, size);

        return questionRepository.findAll(pageable);
    }

    @Override
    public Page<Question> getQuestionPaginationWithSearch(Integer currentPage, Integer size, String keyword) {
        Pageable pageable = PageRequest.of(currentPage, size);
        return questionRepository.getAllByAnswerIsContainingIgnoreCaseOrNameIsContainingIgnoreCase(pageable,keyword,keyword);
    }
}
