package com.rlasb.admin.web;

import com.rlasb.admin.config.auth.LoginUser;
import com.rlasb.admin.config.auth.dto.SessionUser;
import com.rlasb.admin.service.FileService;
import com.rlasb.admin.service.PostsService;
import com.rlasb.admin.web.dto.PostsResponseDto;
import com.rlasb.admin.web.dto.PostsSaveRequestDto;
import com.rlasb.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequiredArgsConstructor
@RestController

public class PostsApiController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    private final FileService fileService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/posts")
    public Long save(@RequestPart(value = "key") PostsSaveRequestDto requestDto,@RequestPart(value="file", required=false) List<MultipartFile> files,@LoginUser SessionUser sessionUser) throws Exception {
        sessionUser = (SessionUser) httpSession.getAttribute("user");
        System.out.println("ApiController");
        System.out.println(sessionUser.getEmail()+"eeeeeeeeeeee");
        return postsService.save(requestDto,files,sessionUser.getEmail());
    }
//    public Long save(@RequestBody PostsSaveRequestDto requestDto, @LoginUser SessionUser sessionUser){
//        sessionUser = (SessionUser) httpSession.getAttribute("user");
//        return postsService.save(requestDto, sessionUser.getEmail());
//    }
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestPart(value = "key") PostsUpdateRequestDto requestDto, @RequestPart(value = "file",required = false) List<MultipartFile>files
                       ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(requestDto.getDeleteList()+"dddd");
        System.out.println(requestDto.getContent()+"cccccccc");
        return postsService.update(id,files,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
//        List<FileResponseDto> fileResponseDtos = fileService.findAllByPosts(id);
//        List<Long> fileId = new ArrayList<>();
//        for (FileResponseDto fileResponseDto : fileResponseDtos) {
//            fileId.add(fileResponseDto.getFileId());
//        }

        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
