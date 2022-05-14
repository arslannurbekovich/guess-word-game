package com.example.testgame.controller;

import com.example.testgame.entity.Question;
import com.example.testgame.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        super();
        this.questionService = questionService;
    }

    @GetMapping("/questions")
    public String listQuestions(Model model,String keyword) {
        return getQuestionPagination(0,model,keyword);
    }

    @GetMapping("/questions/new")
    public String createQuestionForm(Model model) {
        Question question = new Question();
        model.addAttribute("question", question);
        return "create_question";

    }

    @PostMapping("/questions")
    public String saveQuestion(@ModelAttribute("question") @Valid @RequestBody Question question,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "create_question";
        }
        questionService.saveQuestion(question);
        return "redirect:/questions";
    }

    @GetMapping("/questions/edit/{id}")
    public String editQuestionForm(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionService.getQuestionById(id));
        return "edit_question";
    }

    @PostMapping("/questions/{id}")
    public String updateQuestion(@PathVariable Long id,
                                @ModelAttribute("question") @Valid Question question) {

        questionService.updateQuestion(question,id);
        return "redirect:/questions";
    }

    @GetMapping("/questions/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return "redirect:/questions";
    }

    @GetMapping("/questions/page/{page}")
    public String getQuestionPagination(@PathVariable Integer page,Model model,String keyword){

        if (keyword != null){
            Page<Question> questionPageSearch = questionService.getQuestionPaginationWithSearch(page,5,keyword);

            model.addAttribute("questions", questionPageSearch);
            model.addAttribute("currentPage",page);
            model.addAttribute("totalPage",questionPageSearch.getTotalPages());
            model.addAttribute("totalItem",questionPageSearch.getTotalElements());
        } else {
            Page<Question> questionPage = questionService.getQuestionPagination(page,5);

            model.addAttribute("questions", questionPage);
            model.addAttribute("currentPage",page);
            model.addAttribute("totalPage",questionPage.getTotalPages());
            model.addAttribute("totalItem",questionPage.getTotalElements());
        }
        return "questions";

    }
}
