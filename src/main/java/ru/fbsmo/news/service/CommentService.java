package ru.fbsmo.news.service;


import ru.fbsmo.news.entity.Comment;

import java.util.List;

public interface CommentService {

    long createComment(Long postId, String content);

    List<Comment> findByPost(long postId);

}
