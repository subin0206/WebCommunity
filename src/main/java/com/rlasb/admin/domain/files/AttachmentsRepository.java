package com.rlasb.admin.domain.files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttachmentsRepository extends JpaRepository<Attachments, Long> {
    public List<Attachments> findAllByPostsId(Long postsId);
    @Modifying
    @Query(value = "DELETE FROM Attachments a"+"WHERE a.id IN (:deleteFileList)", nativeQuery = true)
    public void deleteByAttachIdList(@Param(("deleteFileList")) List<Long> deleteFileList);
}
