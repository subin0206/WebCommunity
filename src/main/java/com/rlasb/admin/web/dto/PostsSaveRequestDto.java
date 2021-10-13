package com.rlasb.admin.web.dto;

import com.rlasb.admin.domain.posts.Posts;
import com.rlasb.admin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private User user;

    @Builder
    public PostsSaveRequestDto(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
