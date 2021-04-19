package ru.gladyshev.demo.twitter.service;


import ru.gladyshev.demo.twitter.entity.Comment;

import java.util.List;

public interface CommentService {

    long createComment(Long postId, String content);

    List<Comment> findByPost(long postId);

}
