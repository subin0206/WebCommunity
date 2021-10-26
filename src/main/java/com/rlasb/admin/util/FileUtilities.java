package com.rlasb.admin.util;

import com.rlasb.admin.domain.files.Attachments;
import com.rlasb.admin.domain.files.FileException;
import com.rlasb.admin.domain.posts.Posts;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtilities {
    public final static String rootPath = Paths.get("C:", "Users", "Hello","Desktop","testFile").toString();

    //MultipartFile 형태 파일 Attachments Entity 형태로 파싱

    public static List<Attachments> parseFileInfo(List<MultipartFile> multipartFiles, Posts posts) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //no file attachments
        if (CollectionUtils.isEmpty(multipartFiles)) {
            return Collections.emptyList();
        }
        String savePath = Paths.get(rootPath, "files").toString();
        if (!new File(savePath).exists()) {
            try{
                new File(savePath).mkdir();
            }catch (Exception e){
                e.getStackTrace();
            }
        }
        List<Attachments> fileList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String origFilename = multipartFile.getOriginalFilename();
            if(origFilename == null || "".equals(origFilename)) continue;
            String filename = MD5Generator(FilenameUtils.getBaseName(origFilename)).toString();
            String filepath = Paths.get(savePath, filename).toString();

            Attachments attachments = new Attachments(posts, multipartFile.getOriginalFilename(),filename, filepath, multipartFile.getSize());
            fileList.add(attachments);

            try {
                File file = new File(filepath);
                multipartFile.transferTo(file);
                file.setWritable(true);
                file.setReadable(true);

            } catch (IOException e) {
                throw new FileException("[" + multipartFile.getOriginalFilename() + "] failed to save file");
            } catch (Exception e) {
                throw  new FileException("[" + multipartFile.getOriginalFilename() + "] failed to save file");
            }
        }
        return fileList;
    }
    //file 중복 방지 암호화 MD5
    public static  String MD5Generator(String input)throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest mdMD5 = MessageDigest.getInstance("MD5");
        mdMD5.update(input.getBytes("UTF-8"));
        byte[] md5Hash = mdMD5.digest();
        StringBuilder hexMD5hash = new StringBuilder();
        for (byte b : md5Hash) {
            String hexString = String.format("%02x", b);
            hexMD5hash.append(hexString);
        }
        return hexMD5hash.toString();
    }

    public static MediaType getMediaType(String filename) {
        String contentType = FilenameUtils.getExtension(filename);
        MediaType mediaType = null;
        if (contentType.equals("pnv")) {
            mediaType = MediaType.IMAGE_PNG;
        } else if (contentType.equals("jpeg") || contentType.equals("jpg")) {
            mediaType = MediaType.IMAGE_JPEG;
        } else if (contentType.equals("gif")) {
            mediaType = MediaType.IMAGE_GIF;
        }
        return mediaType;
    }

}
