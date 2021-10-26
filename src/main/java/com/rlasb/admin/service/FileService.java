package com.rlasb.admin.service;

import com.rlasb.admin.domain.files.Attachments;
import com.rlasb.admin.domain.files.AttachmentsRepository;
import com.rlasb.admin.web.dto.FileResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FileService {
    private final AttachmentsRepository attachmentsRepository;

//    @Transactional(readOnly = true)
//    public
    @Transactional(readOnly = true)
    public List<FileResponseDto> findAllByPosts(Long postsId){
        List<Attachments> attachmentsList = attachmentsRepository.findAllByPostsId(postsId);
        return attachmentsList.stream().map(FileResponseDto::new)
                .collect(Collectors.toList());
    }
}
