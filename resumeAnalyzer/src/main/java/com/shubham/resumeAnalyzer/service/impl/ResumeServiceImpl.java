package com.shubham.resumeAnalyzer.service.impl;

import com.shubham.resumeAnalyzer.dto.resume.ResumeRequest;
import com.shubham.resumeAnalyzer.dto.resume.ResumeResponse;
import com.shubham.resumeAnalyzer.entity.Resume;
import com.shubham.resumeAnalyzer.entity.ResumeStatus;
import com.shubham.resumeAnalyzer.entity.User;
import com.shubham.resumeAnalyzer.repository.ResumeRepository;
import com.shubham.resumeAnalyzer.repository.UserRepository;
import com.shubham.resumeAnalyzer.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl
        implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    @Override
    public ResumeResponse createResume(
            ResumeRequest request) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email =
                authentication.getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        Resume resume =
                Resume.builder()
                        .title(request.getTitle())
                        .status(
                                ResumeStatus.UPLOADED
                        )
                        .uploadedAt(
                                LocalDateTime.now()
                        )
                        .updatedAt(
                                LocalDateTime.now()
                        )
                        .user(user)
                        .build();

        Resume savedResume =
                resumeRepository.save(resume);

        return ResumeResponse.builder()
                .id(savedResume.getId())
                .title(savedResume.getTitle())
                .status(
                        savedResume
                                .getStatus()
                                .name()
                )
                .build();
    }

    @Override
    public List<ResumeResponse> getMyResumes() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email =
                authentication.getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return resumeRepository
                .findByUser(user)
                .stream()
                .map(resume ->
                        ResumeResponse.builder()
                                .id(resume.getId())
                                .title(
                                        resume.getTitle()
                                )
                                .status(
                                        resume
                                                .getStatus()
                                                .name()
                                )
                                .build()
                )
                .toList();
    }
}