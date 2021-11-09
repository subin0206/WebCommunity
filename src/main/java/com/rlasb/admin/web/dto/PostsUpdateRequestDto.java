package com.rlasb.admin.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Getter
@RequiredArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private ArrayList<Long> deleteList;

    @Builder
    public PostsUpdateRequestDto(String title, String content, ArrayList<Long> deleteList) {
        this.title = title;
        this.content = content;
        this.deleteList = deleteList;
    }
}
