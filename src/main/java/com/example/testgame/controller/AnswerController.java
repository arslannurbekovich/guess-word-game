package com.example.testgame.controller;

import com.example.testgame.entity.Answer;
import com.example.testgame.service.AnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        return getAnswerPagination(id,0,model);
    }

    @GetMapping("/sessions/{id}/{page}")
    public String getAnswerPagination(@PathVariable Long id,@PathVariable Integer page, Model model){

        int limit = 5;
        Integer sizeOffset = (limit*page);

        List<Answer> answerPage = answerService.findAnswersByUserWithTotalPage(id,sizeOffset);

        Integer totalElements = answerService.getTotalAnswersByUser(id);

        Integer totalPage = (int)Math.ceil(((double)totalElements/limit));

        model.addAttribute("sessions", answerPage);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("totalItem",totalElements);
        model.addAttribute("userId",id);

        return "answer_session";
    }

}
