package com.example.testgame.controller;

import com.example.testgame.service.AnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {

    private final AnswerService answerService;

    public Controller(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping ("/save")
    public String saveSessionByUser(@RequestBody Map<String, String> history){
        answerService.saveAnswer(history);
        return "Результат игры сохранён!";
    }
}
