package com.example.testgame.controller;

import com.example.testgame.dto.UserRegistrationDto;
import com.example.testgame.entity.User;
import com.example.testgame.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto registrationDto,
                                      BindingResult result) {
        User existingUsername = userService.getUser(registrationDto.getUsername());

        if (existingUsername != null){
            result.rejectValue("username", null,"Логин с таким именем уже существует!");
        }

        if (result.hasErrors()){
            return "/registration";
        }

        userService.save(registrationDto);

        return "redirect:/registration?success";
    }

}
