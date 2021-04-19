package ru.gladyshev.demo.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gladyshev.demo.twitter.entity.Comment;
import ru.gladyshev.demo.twitter.repository.CommentRepository;
import ru.gladyshev.demo.twitter.repository.PostRepository;
import ru.gladyshev.demo.twitter.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }



    @Override
    @PreAuthorize("hasRole('USER')")
    public long createComment(Long postId, String content) {
        Comment comment = new Comment();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        comment.setPost(postRepository.findById(postId).orElseThrow());
        comment.setUser(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username)));
        comment.setContent(content);
        comment.setDtCreated(LocalDateTime.now());
        commentRepository.save(comment);

        return comment.getCommentId();
    }

    @Override
    public List<Comment> findByPost(long postId) {
        return commentRepository.findByPost_PostId(postId);
    }
}