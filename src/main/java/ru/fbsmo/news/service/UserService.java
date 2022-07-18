package ru.fbsmo.news.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.fbsmo.news.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    void create(User user);

    User findByUsername(String username);



}
