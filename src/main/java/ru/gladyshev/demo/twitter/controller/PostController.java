package ru.gladyshev.demo.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gladyshev.demo.twitter.repository.TagRepository;
import ru.gladyshev.demo.twitter.repository.UserRepository;
import ru.gladyshev.demo.twitter.service.PostService;
import ru.gladyshev.demo.twitter.service.UserService;

import javax.servlet.ServletContext;

@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final ServletContext context;

    @Autowired
    public PostController(PostService postService, UserService userService, UserRepository userRepository, TagRepository tagRepository, ServletContext context) {
        this.postService = postService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.context = context;
    }


    @GetMapping("/hello")
    public String hello(ModelMap model){
        model.put("value", "HELLO!!");
        return "hello";
    }


    @GetMapping("/")
    public String index(ModelMap model, @RequestParam(required = false) String search){
        if(search != null ) {
            model.put("posts",  postService.search(search));
            model.put("title", "Search by");
            model.put("subtitle", search);
            model.put("users", userService.getAllUsers());
            return "blog";
        } else {
            model.put("title", "All posts");
            model.put("posts", postService.getAllPosts());
            model.put("users", userService.getAllUsers());
            setCommonParams(model);
            return "blog";
        }
    }


    @GetMapping("/user/{username}")
    public String user(ModelMap modelMap, @PathVariable String username){
        modelMap.put("title", "Found by user");
        modelMap.put("subtitle", username);
        modelMap.put("posts", postService.findByUser(username));
        setCommonParams(modelMap);
        return "blog";
    }

    private void setCommonParams(ModelMap model){
        model.put("users", userRepository.findAll(Sort.by("username")));
    }

}
