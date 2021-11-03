package com.rlasb.admin.web.dto;

import com.rlasb.admin.domain.Gallery.Gallerys;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {
    private Long id;
    private String origFileName;
    private String fileName;
    private String filePath;
    private Long fileSize;

    public GalleryDto(Gallerys entity) {
        this.id = entity.getId();
    }

}
