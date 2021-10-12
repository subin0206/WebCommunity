package com.rlasb.admin.web.dto;

import com.rlasb.admin.domain.posts.Posts;
import com.rlasb.admin.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private User author;
    private LocalDateTime modifyDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifyDate = entity.getModifyDate();
    }
}
