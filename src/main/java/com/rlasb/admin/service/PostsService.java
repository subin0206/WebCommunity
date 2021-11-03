package com.rlasb.admin.service;

import com.rlasb.admin.config.auth.LoginUser;
import com.rlasb.admin.config.auth.dto.SessionUser;
import com.rlasb.admin.domain.Gallery.Gallerys;
import com.rlasb.admin.domain.Gallery.GallerysRepository;
import com.rlasb.admin.domain.posts.PostRepository;
import com.rlasb.admin.domain.posts.Posts;
import com.rlasb.admin.domain.user.User;
import com.rlasb.admin.domain.user.UserRepository;
import com.rlasb.admin.utility.FileUtilities;
import com.rlasb.admin.web.PostsApiController;
import com.rlasb.admin.web.dto.PostsListResponseDto;
import com.rlasb.admin.web.dto.PostsResponseDto;
import com.rlasb.admin.web.dto.PostsSaveRequestDto;
import com.rlasb.admin.web.dto.PostsUpdateRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final GallerysRepository gallerysRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto, String email, List<MultipartFile> files) throws Exception{

        User users = userRepository.findByEmail(email);
        requestDto.setUser(users);
        Posts savePosts = postRepository.save(requestDto.toEntity());

        List<Gallerys> GallerysList = FileUtilities.parseFileInfo(files, savePosts);
        //파일이 존재할 경우
        if(!GallerysList.isEmpty()){
            GallerysList.forEach(gallerys -> gallerysRepository.save(gallerys));
        }
        //삭제할 파일이 존재할 경우
        /*if (!deleteFileList.isEmpty()){
            gallerysRepository.deleteByAttachIdList(deleteFileList);
        }*/

        return savePosts.getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id ="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));
        postRepository.delete(posts);
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        List<PostsListResponseDto> responseDtos = new ArrayList<>();
        return postRepository.findAllByOrderByIdDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }


}
