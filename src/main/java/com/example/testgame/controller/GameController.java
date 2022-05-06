package com.example.testgame.controller;

import com.example.testgame.service.GameService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/game")
    public String game(Model model) {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String username = user.getName();
        model.addAttribute("game", gameService.getRandomQuestion());
        model.addAttribute("current_user", username);
        return "game";
    }

    @GetMapping("/start")
    public String start() {
        return "start_game";
    }

}
