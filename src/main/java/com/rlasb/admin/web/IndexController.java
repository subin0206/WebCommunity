package com.rlasb.admin.web;

import com.rlasb.admin.config.auth.LoginUser;
import com.rlasb.admin.config.auth.dto.SessionUser;
import com.rlasb.admin.domain.user.UserRepository;
import com.rlasb.admin.service.FileService;
import com.rlasb.admin.service.PostsService;
import com.rlasb.admin.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;
    private final FileService fileService;
    UserRepository userRepository;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
//        for(int i =0; i < postsService.findAllDesc().size(); i++){
//            model.addAttribute("usersPost", postsService.getEmail().);
//        }
        if (user != null) {
            model.addAttribute("userId", user.getEmail());
        }
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(Model model,@LoginUser SessionUser user){
        if (user != null) {
            model.addAttribute("userId", user.getName());
        }
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        model.addAttribute("fileList", postsService.getAttachmentsRepository().findAllByPostsId(id));
        return "posts-update";
    }

    @GetMapping("/posts/detail/{id}")
    public String postsDetail(@PathVariable Long id, Model model){
        model.addAttribute("posts",postsService.findById(id));
        model.addAttribute("fileList", postsService.getAttachmentsRepository().findAllByPostsId(id));
        return "posts-detail";
    }
}
