package com.rlasb.admin.domain.Gallery;

import com.rlasb.admin.domain.BaseTimeEntity;
import com.rlasb.admin.domain.posts.Posts;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "Gallerys")
@Table(name = "GALLERYS")
public class Gallerys extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Gallerys_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Posts_id")
    private Posts posts;

    @Column(nullable = false)
    private String origFileName; //파일 원본명

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath; //파일 저장경로

    private Long fileSize;

    @Builder
    public Gallerys(Posts posts, String origFileName, String fileName, String filePath, Long fileSize){
        this.posts = posts;
        this.origFileName = origFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void setPosts(Posts posts){
        this.posts = posts;
    }

}
