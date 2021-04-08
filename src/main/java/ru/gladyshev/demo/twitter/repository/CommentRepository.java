package ru.gladyshev.demo.twitter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gladyshev.demo.twitter.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost_PostIdAndUser_UserId(long postId, long userId);
    List<Comment> findByPost_PostId(long postId);
}
