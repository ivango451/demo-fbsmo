package ru.gladyshev.demo.twitter.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.gladyshev.demo.twitter.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void create(User user);

    User findByUsername(String username);
}
