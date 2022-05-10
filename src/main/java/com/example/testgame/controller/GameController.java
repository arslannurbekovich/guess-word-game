package com.example.testgame.controller;

import com.example.testgame.entity.Question;
import com.example.testgame.service.GameService;
import com.example.testgame.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class GameController {

    private final GameService gameService;
    private final QuestionService questionService;

    public GameController(GameService gameService, QuestionService questionService) {
        this.gameService = gameService;
        this.questionService = questionService;
    }

    @GetMapping("/game")
    public String game(Model model) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String username = user.getName();
        log.info(" Authentication username:" + username);
        model.addAttribute("game", gameService.getRandomQuestion());
        model.addAttribute("current_user", username);
        return "game";
    }

    @GetMapping("/start")
    public String start(Model model) {
        List<Question> questionList = questionService.getAllQuestions();
        model.addAttribute("questionList",questionList);
        return "start_game";
    }

}
