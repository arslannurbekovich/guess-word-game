package com.example.testgame.controller;

import com.example.testgame.entity.User;
import com.example.testgame.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.format.DateTimeFormatter;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/users")
    public String findAllUsers(Model model, String keyword) {
        model.addAttribute("dateFormat", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return getPagination(0,model,keyword);
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("users/page/{page}")
    public String getPagination(@PathVariable int page, Model model,String keyword){

        model.addAttribute("currentPage",page);

        if (keyword != null){
            Page<User> userPageWithSearch = userService.getUsersPaginationAndWithSearch(page,5,keyword);
            model.addAttribute("users",userPageWithSearch);
            model.addAttribute("totalPage",userPageWithSearch.getTotalPages());
            model.addAttribute("totalItem",userPageWithSearch.getTotalElements());
        } else {
            Page<User> userPage = userService.getUsersPagination(page,5);
            model.addAttribute("users", userPage);
            model.addAttribute("totalPage",userPage.getTotalPages());
            model.addAttribute("totalItem",userPage.getTotalElements());
        }

        return "users";
    }

}
