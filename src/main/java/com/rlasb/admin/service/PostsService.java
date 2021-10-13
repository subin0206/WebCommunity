package com.rlasb.admin.service;

import com.rlasb.admin.domain.posts.PostRepository;
import com.rlasb.admin.domain.posts.Posts;
import com.rlasb.admin.domain.user.UserRepository;
import com.rlasb.admin.web.dto.PostsListResponseDto;
import com.rlasb.admin.web.dto.PostsResponseDto;
import com.rlasb.admin.web.dto.PostsSaveRequestDto;
import com.rlasb.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto, String userEmail) {
        requestDto.setUser(userRepository.findByEmail(userEmail));
        return postRepository.save(requestDto.toEntity()).getId();
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
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다."));
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
//        postRepository.findAllDesc().stream().forEach(email->email.getUser().getEmail());
//        personList.stream().forEach(person -> person.setName(person.getName() + " aa"));
//        postRepository.findAllDesc().stream()

        return postRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public String getEmail() {
        List<String> emails = new ArrayList<>();
        String email = "";
//        List<Long> postsId = new ArrayList<>();
//        postRepository.findAllDesc().stream()
//                .map(oc -> oc.getId())
//                .forEach(oc -> postsId.add(oc));
//
        postRepository.findAllDesc().stream()
			.map(oc -> oc.getUser().getEmail())
                .forEach(oc -> emails.add(oc));
//        for (int i = 0; i < emails.size(); i++) {
//
//        }
//        emails.iterator().next();
        return emails.iterator().next();
    }
}
