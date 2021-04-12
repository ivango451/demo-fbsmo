package ru.gladyshev.demo.twitter.SpringDataJpaTest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.gladyshev.demo.twitter.config.JpaConfig;
import ru.gladyshev.demo.twitter.entity.Post;
import ru.gladyshev.demo.twitter.service.PostService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JpaConfig.class)
@Sql(scripts = "classpath:Blog.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PostServiceTest {

    private final PostService postService;

    @Autowired
    public PostServiceTest(PostService postService) {
        this.postService = postService;
    }


    @Test
    void getAllPosts(){

        Set<String> posts = postService.getAllPosts().stream().map(Post::getTitle).collect(Collectors.toSet());
        assertEquals(Set.of("Day 1", "Day 2", "Day 3"), posts);
    }

}
