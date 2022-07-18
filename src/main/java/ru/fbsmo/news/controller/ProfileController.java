package ru.fbsmo.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.fbsmo.news.repository.ProfileRepository;
import ru.fbsmo.news.repository.TagRepository;
import ru.fbsmo.news.repository.UserRepository;
import ru.fbsmo.news.service.PostService;
import ru.fbsmo.news.util.SecurityUtils;
import ru.fbsmo.news.service.UserService;

import javax.servlet.ServletContext;

@Controller
public class ProfileController {

    private final ProfileRepository profileRepository;
    private final PostService postService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final ServletContext context;


    @Autowired
    public ProfileController(ProfileRepository profileRepository, PostService postService, UserService userService, UserRepository userRepository, TagRepository tagRepository, ServletContext context) {
        this.profileRepository = profileRepository;
        this.postService = postService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.context = context;
    }


    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public String profile(ModelMap modelMap){
        modelMap.put("username", SecurityUtils.getCurrentUserDetails().getUsername());
        return "/profile";
    }



    private void setCommonParams(ModelMap model){
        model.put("user", userRepository.findAll(Sort.by("username")));
        model.put("tags", tagRepository.findAll());
        model.put("contextPath", context.getContextPath());
    }


}
