package com.rlasb.admin.service;

import com.rlasb.admin.domain.files.Attachments;
import com.rlasb.admin.domain.files.AttachmentsRepository;
import com.rlasb.admin.domain.posts.PostRepository;
import com.rlasb.admin.domain.posts.Posts;
import com.rlasb.admin.domain.user.UserRepository;
import com.rlasb.admin.util.FileUtilities;
import com.rlasb.admin.web.dto.PostsListResponseDto;
import com.rlasb.admin.web.dto.PostsResponseDto;
import com.rlasb.admin.web.dto.PostsSaveRequestDto;
import com.rlasb.admin.web.dto.PostsUpdateRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Getter
public class PostsService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AttachmentsRepository attachmentsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto, List<MultipartFile> files,String userEmail) throws Exception{

        System.out.println(userEmail+"userEmail");
        requestDto.setUser(userRepository.findByEmail(userEmail));
        Posts savePosts = postRepository.save(requestDto.toEntity());

        List<Attachments> attachmentsList = FileUtilities.parseFileInfo(files, savePosts);
        if (!attachmentsList.isEmpty()) {
            attachmentsList.forEach(attachments -> attachmentsRepository.save(attachments));
        }
//        if (!deleteFileList.isEmpty()) {
//            attachmentsRepository.deleteByAttachIdList(deleteFileList);
//        }
        return savePosts.getId();
    }

    @Transactional
    public Long update(Long id, List<MultipartFile> files, PostsUpdateRequestDto requestDto) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Posts posts = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id ="+id));

        if (!requestDto.getDeleteList().isEmpty()) {
            attachmentsRepository.deleteByAttachIdList(requestDto.getDeleteList());
        }
        posts.update(requestDto.getTitle(), requestDto.getContent());

        List<Attachments> attachmentsList = FileUtilities.parseFileInfo(files, posts);

        if (!attachmentsList.isEmpty()) {
            attachmentsList.forEach(attachments -> attachmentsRepository.save(attachments));
        }
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
