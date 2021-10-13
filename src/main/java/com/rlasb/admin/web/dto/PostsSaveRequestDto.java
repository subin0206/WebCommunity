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
    //private String author;
    private User author;

    @Builder
    public PostsSaveRequestDto(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

    public void setUser(User user){
        this.author = user;
    }

}
