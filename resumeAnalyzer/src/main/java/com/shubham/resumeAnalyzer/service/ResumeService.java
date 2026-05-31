package com.shubham.resumeAnalyzer.service;

import com.shubham.resumeAnalyzer.dto.resume.ResumeRequest;
import com.shubham.resumeAnalyzer.dto.resume.ResumeResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResumeService {

    ResumeResponse createResume(
            ResumeRequest request);

    List<ResumeResponse> getMyResumes();

    void deleteResume(Long id);
    ResumeResponse getResumeById(Long id);

    ResumeResponse updateResume(
            Long id,
            ResumeRequest request
    );
    ResumeResponse uploadResume(
            String title,
            MultipartFile file
    );
}