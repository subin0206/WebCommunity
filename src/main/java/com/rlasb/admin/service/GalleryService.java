package com.rlasb.admin.service;

import com.rlasb.admin.domain.Gallery.Gallerys;
import com.rlasb.admin.domain.Gallery.GallerysRepository;
import com.rlasb.admin.web.dto.GalleryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GalleryService {
    private final GallerysRepository galleryRepository;

    @Transactional(readOnly = true)
    public List<GalleryDto> findAllByPosts(Long postid){
        List<Gallerys> gallerysList = galleryRepository.findAllByPostsId(postid);
        return gallerysList.stream().map(GalleryDto::new).collect(Collectors.toList());
    }

    //불러오기
    /*@Transactional
    public GalleryDto getGallery(Long id){
        Gallerys gallery = galleryRepository.findById(id).get();
        return GalleryDto.builder()
                .id(id)
                .origFileName(gallery.getOrigFileName())
                .fileName(gallery.getFileName())
                .filePath(gallery.getFilePath())
                .fileSize(gallery.getFileSize())
                .build();
    }*/
}
