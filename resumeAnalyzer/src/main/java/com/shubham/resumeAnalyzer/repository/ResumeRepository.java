package com.shubham.resumeAnalyzer.repository;

import com.shubham.resumeAnalyzer.entity.Resume;
import com.shubham.resumeAnalyzer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository
        extends JpaRepository<Resume, Long> {

    List<Resume> findByUser(User user);
}