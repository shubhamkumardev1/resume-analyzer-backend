package com.shubham.resumeAnalyzer.repository;

import com.shubham.resumeAnalyzer.entity.AnalysisResult;
import com.shubham.resumeAnalyzer.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalysisResultRepository
        extends JpaRepository<
        AnalysisResult,
        Long> {

    List<AnalysisResult> findByResume(
            Resume resume
    );
}