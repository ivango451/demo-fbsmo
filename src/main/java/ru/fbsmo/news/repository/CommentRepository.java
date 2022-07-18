package ru.fbsmo.news.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.fbsmo.news.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost_PostIdAndUser_UserId(long postId, long userId);
    List<Comment> findByPost_PostId(long postId);
}
