package ru.fbsmo.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.fbsmo.news.repository.CommentRepository;
import ru.fbsmo.news.repository.UserRepository;
import ru.fbsmo.news.dto.PostDto;
import ru.fbsmo.news.repository.TagRepository;
import ru.fbsmo.news.service.PostService;
import ru.fbsmo.news.service.UserService;

import javax.servlet.ServletContext;

import static ru.fbsmo.news.util.PostUtils.toDto;

@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final ServletContext context;

    @Autowired
    public PostController(PostService postService, UserService userService, UserRepository userRepository, TagRepository tagRepository, CommentRepository commentRepository, ServletContext context) {
        this.postService = postService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
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
            model.put("users", userService.findAll());
            return "blog";
        } else {
            model.put("title", "All posts");
            model.put("posts", postService.getAllPosts());
            model.put("users", userService.findAll());
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


    @GetMapping("/tag/{name}")
    public String tag(ModelMap model, @PathVariable String name){
        model.put("title", "Find by tag");
        model.put("subtitle", name);
        model.put("posts", postService.findByTag(name));
        setCommonParams(model);
        return "blog";
    }

    @GetMapping("/post/new")
    @PreAuthorize("hasRole('USER')")
    public String postNew(ModelMap modelMap) {
        setCommonParams(modelMap);
        return "post-new";
    }

    @PostMapping("/post/new")
    public String postNew(PostDto postDto) {
        postService.createPost(postDto);
        return "redirect:/";
    }

    @GetMapping("/post/{postId}/edit")
    public String postEdit(ModelMap modelMap,
                           @PathVariable long postId) {
        postService.checkAuthority(postId);
        modelMap.put("post", toDto(postService.findById(postId)));
        setCommonParams(modelMap);
        return "post-edit";
    }

    @PostMapping("/post/edit")
    public String postEdit(PostDto postDto) {
        postService.update(postDto);
        return "redirect:/";
    }


    @GetMapping("/post/{id}")
    public String post(@PathVariable long id,
                       ModelMap modelMap){
        modelMap.put("post", postService.findById(id));
        modelMap.put("comments", commentRepository.findByPost_PostId(id));
        setCommonParams(modelMap);
        return "post";
    }


    @PostMapping("/post/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id){
        postService.delete(id);
    }


    private void setCommonParams(ModelMap model){
        model.put("users", userRepository.findAll(Sort.by("username")));
        model.put("tags", tagRepository.findAll());
    }

}
