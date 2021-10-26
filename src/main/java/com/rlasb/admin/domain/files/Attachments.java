package com.rlasb.admin.domain.files;

import com.rlasb.admin.domain.BaseTimeEntity;
import com.rlasb.admin.domain.posts.Posts;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "ATTACHMENTS")
public class Attachments extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACHMENTS_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Posts posts;

    @Column(nullable = false)
    private String origFileName;
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private String filePath;

    private Long fileSize;

    @Builder
    public Attachments(Posts posts, String origFileName, String fileName, String filePath, Long fileSize) {
        this.posts = posts;
        this.origFileName = origFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }
}
