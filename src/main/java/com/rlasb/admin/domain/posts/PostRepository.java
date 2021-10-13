package com.rlasb.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {
    @Query("Select p From Posts p Order By p.id Desc")
    List<Posts> findAllDesc();

}
