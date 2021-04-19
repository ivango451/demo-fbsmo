package ru.gladyshev.demo.twitter.util;

import ru.gladyshev.demo.twitter.dto.PostDto;
import ru.gladyshev.demo.twitter.entity.Post;
import  ru.gladyshev.demo.twitter.entity.Tag;

import java.util.stream.Collectors;

public class PostUtils {

    public static PostDto toDto(Post post) {
        PostDto dto = new PostDto();
        dto.setPostId(post.getPostId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setTags(post.getTags()
                .stream()
                .map(Tag::getName)
                .collect(Collectors.joining(" ")));
        dto.setUsername(post.getUser().getUsername());
        dto.setDtCreated(post.getDtCreated());
        dto.setDtUpdated(post.getDtUpdated());

        return dto;
    }
}
