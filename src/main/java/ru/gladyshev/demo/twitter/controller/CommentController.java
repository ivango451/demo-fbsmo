package ru.gladyshev.demo.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gladyshev.demo.twitter.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public String create(Long postId, String content) {
        commentService.createComment(postId, content);
        return "redirect:/post/" + postId;
    }

}
