package com.example.testgame.controller;

import com.example.testgame.dto.AnswerStatusDto;
import com.example.testgame.entity.Answer;
import com.example.testgame.enums.Status;
import com.example.testgame.service.AnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
    public String findSessionByUser(@PathVariable Long id,
                                    Model model,
                                    @Valid String values) {

        return getAnswerPagination(id, 0, model, values);
    }

    @GetMapping("/sessions/{id}/{page}")
    public String getAnswerPagination(@PathVariable Long id,
                                      @PathVariable Integer page,
                                      Model model,
                                      String values) {
        int limit = 5;
        Integer sizeOffset = (limit * page);

        List<AnswerStatusDto> statusList = answerService.getAllStatuses();

        model.addAttribute("currentPage", page);
        model.addAttribute("userId", id);
        model.addAttribute("statusList", statusList);

        if (values != null) {
            Status status = Status.valueOf(values);

            List<Answer> answerPage = answerService.findAnswersByUserWithTotalPageWithFilter(id, sizeOffset, values);

            Integer totalElements = answerService.getTotalAnswersByUserWithFilter(id, status);

            Integer totalPage = answerService.getTotalPages(totalElements,limit);

            model.addAttribute("sessions", answerPage);
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("totalItem", totalElements);
            model.addAttribute("filterStatus", true);
            model.addAttribute("value",values);

        } else {

            List<Answer> answerPage = answerService.findAnswersByUserWithTotalPage(id, sizeOffset);

            Integer totalElements = answerService.getTotalAnswersByUser(id);

            Integer totalPage = answerService.getTotalPages(totalElements,limit);

            model.addAttribute("sessions", answerPage);
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("totalItem", totalElements);
            model.addAttribute("filterStatus",false);
        }

        return "answer_session";
    }

}
