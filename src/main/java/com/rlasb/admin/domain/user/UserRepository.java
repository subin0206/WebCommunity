package com.rlasb.admin.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    @Query(value = "Select email From User Where user id = ?0", nativeQuery = true)
    String findUserEmail(Long id);
}
