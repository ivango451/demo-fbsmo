package ru.gladyshev.demo.twitter.service;

import ru.gladyshev.demo.twitter.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    List<Post> search(String key);

    List<Post> findByUser(String username);

    List<Post> findByTag(String username);
}
