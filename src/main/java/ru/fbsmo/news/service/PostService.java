package ru.fbsmo.news.service;

import ru.fbsmo.news.dto.PostDto;
import ru.fbsmo.news.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    List<Post> search(String key);

    List<Post> findByUser(String username);

    List<Post> findByTag(String tagName);

    long createPost(PostDto postDto);

    void checkAuthority(long postId);

    void update(PostDto postDto);

    void delete(long postId);

    Post findById(long postId);
}
