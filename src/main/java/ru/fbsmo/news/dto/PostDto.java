package ru.fbsmo.news.dto;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long postId;
    private String title;
    private String content;
    private String tags;
    private String username;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;


}
