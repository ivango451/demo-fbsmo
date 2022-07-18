package ru.fbsmo.news.util;

import ru.fbsmo.news.entity.Tag;
import ru.fbsmo.news.dto.PostDto;
import ru.fbsmo.news.entity.Post;

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
