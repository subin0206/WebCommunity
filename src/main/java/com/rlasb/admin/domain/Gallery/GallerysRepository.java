package com.rlasb.admin.domain.Gallery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.*;
import java.util.List;

public interface GallerysRepository extends JpaRepository<Gallerys, Long> {
    public List<Gallerys> findAllByPostsId(Long postsId);

    @Modifying
    @Query(value = "DELETE FROM Attachments a " + "WHERE a.id IN (:deleteFileList)",nativeQuery = true)
    public void deleteByAttachIdList(@Param("deleteFileList") List<Long> deleteFileList);
}
