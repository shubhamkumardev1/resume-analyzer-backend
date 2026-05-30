package com.shubham.resumeAnalyzer.repository;

import com.shubham.resumeAnalyzer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
