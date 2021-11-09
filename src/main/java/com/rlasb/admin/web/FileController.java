package com.rlasb.admin.web;

import com.rlasb.admin.domain.files.Attachments;
import com.rlasb.admin.service.FileService;
import com.rlasb.admin.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class FileController {
    private final FileService fileService;
    private final PostsService postsService;

    @CrossOrigin
    @GetMapping(
            value = "/image/{id}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}
    )
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        Optional<Attachments> optAttach = postsService.getAttachmentsRepository().findById(id);
                Attachments attachments = optAttach.get();

        String path = attachments.getFilePath();
        InputStream imageStream = new FileInputStream(path);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }
}
