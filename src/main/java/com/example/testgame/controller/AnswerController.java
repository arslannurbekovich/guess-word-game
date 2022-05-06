package com.example.testgame.controller;

import com.example.testgame.service.AnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/session")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        super();
        this.answerService = answerService;
    }


    @GetMapping("/sessions/{id}")
    public String findSessionByUser(@PathVariable Long id, Model model) {
        model.addAttribute("sessions", answerService.getAllByUserId(id));
        return "answer_session";
    }

}
