package ru.gladyshev.demo.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gladyshev.demo.twitter.entity.Post;
import ru.gladyshev.demo.twitter.repository.PostRepository;
import ru.gladyshev.demo.twitter.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }



    @Override
    @Transactional(readOnly = true)
    public List<Post> search(String key) {
        return postRepository.findByContentContainingIgnoreCaseOrderByDtCreatedDesc(key);
    }

    @Override
    public List<Post> findByUser(String username) {
        List<Post> posts = userRepository.findByUsername(username)
                .orElseThrow(NoSuchElementException::new)
                .getPosts();
        posts.size();
        return posts;
    }

    @Override
    public List<Post> findByTag(String username) {
        return postRepository.findByTagName(username);
    }
}
