package com.example.testgame.service.impl;

import com.example.testgame.dao.AnswerRepository;
import com.example.testgame.dto.AnswerStatusDto;
import com.example.testgame.entity.Answer;
import com.example.testgame.entity.Question;
import com.example.testgame.entity.User;
import com.example.testgame.enums.Status;
import com.example.testgame.service.AnswerService;
import com.example.testgame.service.QuestionService;
import com.example.testgame.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final UserService userService;

    public AnswerServiceImpl(AnswerRepository answerRepository,
                             QuestionService questionService, UserService userService) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
        this.userService = userService;
    }


    @Override
    public Answer saveAnswer(Map<String, String> history) {
        Answer answer = new Answer();
        String answerByUser = history.get("answer").toUpperCase();
        Question question = questionService.findQuestionByAnswer(answerByUser);

        User user = userService.getUser(history.get("user"));

        answer.setQuestion(question);
        answer.setAttempts(history.get("mistakes"));
        answer.setStatus(Status.valueOf(history.get("status")));
        answer.setUser(user);

        answerRepository.save(answer);

        return answer;
    }

    @Override
    public List<Answer> findAllUsersAnswers(Long userId) {
        return answerRepository.findAllByUserId(userId);
    }

    @Override
    public Integer getTotalAnswersByUser(Long userId) {
        return answerRepository.countAllByUserId(userId);
    }

    @Override
    public Integer getTotalPages(Integer totalElements, int limit) {
        return (int) Math.ceil(((double) totalElements / limit));
    }


    @Override
    public Integer getTotalAnswersByUserWithFilter(Long userId, Status status) {
        return answerRepository.countAllByUserIdAndAndStatusEquals(userId,status);
    }

    @Override
    public List<Answer> findAnswersByUserWithTotalPage(Long userId, Integer page) {
        return answerRepository.getAnswersByUserWithTotalPage(userId,page);
    }

    @Override
    public List<Answer> findAnswersByUserWithTotalPageWithFilter(Long userId, Integer page, String status) {
        return answerRepository.getAnswersByUserWithTotalPageWithFilter(userId,status,page);
    }

    @Override
    public List<AnswerStatusDto> getAllStatuses() {
        List<AnswerStatusDto> statusList = new ArrayList<>();
        AnswerStatusDto statusDtoWon = new AnswerStatusDto(Status.WON,"???????????????????? ??????????????");
        AnswerStatusDto statusDtoLoss = new AnswerStatusDto(Status.LOSS,"?????????????????????? ??????????????");
        statusList.add(statusDtoLoss);
        statusList.add(statusDtoWon);
        return statusList;
    }

}
