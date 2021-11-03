package com.rlasb.admin.web.dto;

import com.rlasb.admin.domain.posts.Posts;
import com.rlasb.admin.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private User author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();

    }
}
