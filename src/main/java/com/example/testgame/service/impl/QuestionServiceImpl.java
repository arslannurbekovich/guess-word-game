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
        return questionRepository.getAllByIsDeletedFalse();
    }

    @Override
    public Question saveQuestion(Question question) {
        question.setIsDeleted(false);
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(()-> new NotFoundException("Вопрос не найдено!"));
    }

    @Override
    public Question updateQuestion(Question question,Long id) {
        Question updateQuestion = getQuestionById(id);
        updateQuestion.setId(id);
        updateQuestion.setName(question.getName());
        updateQuestion.setAnswer(question.getAnswer());
        return questionRepository.save(updateQuestion);
    }

    @Override
    public void deleteQuestionById(Long id) {
        Question question = getQuestionById(id);
        question.setIsDeleted(true);
        questionRepository.save(question);
    }

    @Override
    public Question findQuestionByAnswer(String answer) {
        return questionRepository.getQuestionByAnswer(answer);
    }

    @Override
    public Page<Question> getQuestionPagination(Integer currentPage, Integer size) {

        Pageable pageable = PageRequest.of(currentPage, size);

        return questionRepository.getAllByIsDeletedFalse(pageable);
    }

    @Override
    public Page<Question> getQuestionPaginationWithSearch(Integer currentPage, Integer size, String keyword) {
        Pageable pageable = PageRequest.of(currentPage, size);
        return questionRepository.getAllByIsDeletedFalseAndAnswerIsContainingIgnoreCaseOrIsDeletedFalseAndNameContainingIgnoreCase(pageable,keyword,keyword);
    }
}
