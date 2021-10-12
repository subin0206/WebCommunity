package com.rlasb.admin.web;

import com.rlasb.admin.config.auth.dto.SessionUser;
import com.rlasb.admin.service.PostsService;
import com.rlasb.admin.web.dto.PostsResponseDto;
import com.rlasb.admin.web.dto.PostsSaveRequestDto;
import com.rlasb.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController

public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(Model model , @RequestBody PostsSaveRequestDto requestDto,
                     @AuthenticationPrincipal SessionUser sessionUser){

        return postsService.save(requestDto, sessionUser.getName());
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
