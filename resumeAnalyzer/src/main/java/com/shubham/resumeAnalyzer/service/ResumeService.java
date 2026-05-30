package com.shubham.resumeAnalyzer.service;

import com.shubham.resumeAnalyzer.dto.resume.ResumeRequest;
import com.shubham.resumeAnalyzer.dto.resume.ResumeResponse;

import java.util.List;

public interface ResumeService {

    ResumeResponse createResume(
            ResumeRequest request);

    List<ResumeResponse> getMyResumes();
}