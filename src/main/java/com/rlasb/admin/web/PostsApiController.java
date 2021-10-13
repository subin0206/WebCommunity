package com.rlasb.admin.web;

import com.rlasb.admin.config.auth.LoginUser;
import com.rlasb.admin.config.auth.dto.SessionUser;
import com.rlasb.admin.service.PostsService;
import com.rlasb.admin.web.dto.PostsResponseDto;
import com.rlasb.admin.web.dto.PostsSaveRequestDto;
import com.rlasb.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController

public class PostsApiController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto, @LoginUser SessionUser sessionUser){
        sessionUser = (SessionUser) httpSession.getAttribute("user");
        return postsService.save(requestDto, sessionUser.getEmail());
    }
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
