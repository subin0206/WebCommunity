package com.rlasb.admin.web.dto;

import com.rlasb.admin.domain.files.Attachments;
import lombok.Getter;

@Getter
public class FileResponseDto {
    private Long fileId;
    public FileResponseDto(Attachments entity){
        this.fileId = entity.getId();
    }
}
