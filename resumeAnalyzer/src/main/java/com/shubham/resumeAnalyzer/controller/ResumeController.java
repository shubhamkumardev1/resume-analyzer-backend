package com.shubham.resumeAnalyzer.controller;

import com.shubham.resumeAnalyzer.dto.resume.ResumeRequest;
import com.shubham.resumeAnalyzer.dto.resume.ResumeResponse;
import com.shubham.resumeAnalyzer.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResumeResponse createResume(
            @Valid
            @RequestBody ResumeRequest request) {

        return resumeService
                .createResume(request);
    }

    @GetMapping
    public List<ResumeResponse> getMyResumes() {

        return resumeService
                .getMyResumes();
    }
    @DeleteMapping("/{id}")
    public String deleteResume(
            @PathVariable Long id) {

        resumeService.deleteResume(id);

        return "Resume deleted successfully";
    }
    @GetMapping("/{id}")
    public ResumeResponse getResumeById(
            @PathVariable Long id) {

        return resumeService
                .getResumeById(id);
    }
    @PutMapping("/{id}")
    public ResumeResponse updateResume(
            @PathVariable Long id,

            @Valid
            @RequestBody
            ResumeRequest request) {

        return resumeService
                .updateResume(
                        id,
                        request
                );
    }
    @PostMapping("/upload")
    public ResumeResponse uploadResume(

            @RequestParam String title,

            @RequestParam MultipartFile file) {

        return resumeService
                .uploadResume(
                        title,
                        file
                );
    }
}