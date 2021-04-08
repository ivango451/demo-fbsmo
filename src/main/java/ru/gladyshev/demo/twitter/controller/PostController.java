package ru.gladyshev.demo.twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.channels.FileChannel;

@Controller
public class PostController {


    @GetMapping("/hello")
    public String hello(ModelMap model){
        model.put("value", "HELLO!!");
        return "hello";
    }


    @GetMapping("/")
    public String index(ModelMap model){
        return "index";
    }

}
