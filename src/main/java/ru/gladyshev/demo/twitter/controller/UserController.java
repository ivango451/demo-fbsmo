package ru.gladyshev.demo.twitter.controller;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gladyshev.demo.twitter.entity.User;
import ru.gladyshev.demo.twitter.service.UserService;
import ru.gladyshev.demo.twitter.service.UserServiceImpl;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String login(ModelMap modelMap){
        return "login";
    }

    @GetMapping("/register")
    public String register(ModelMap modelMap){
        return "register";
    }


    @PostMapping("/register")
    public String register(User user){
        userService.create(user);
        return "redirect:/login";
    }
}
