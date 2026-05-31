package com.shubham.resumeAnalyzer.service.impl;

import com.shubham.resumeAnalyzer.dto.analysis.AnalysisHistoryResponse;
import com.shubham.resumeAnalyzer.dto.analysis.AnalysisResponse;
import com.shubham.resumeAnalyzer.dto.resume.ResumeRequest;
import com.shubham.resumeAnalyzer.dto.resume.ResumeResponse;
import com.shubham.resumeAnalyzer.entity.AnalysisResult;
import com.shubham.resumeAnalyzer.entity.Resume;
import com.shubham.resumeAnalyzer.entity.ResumeStatus;
import com.shubham.resumeAnalyzer.entity.User;
import com.shubham.resumeAnalyzer.repository.AnalysisResultRepository;
import com.shubham.resumeAnalyzer.repository.ResumeRepository;
import com.shubham.resumeAnalyzer.repository.UserRepository;
import com.shubham.resumeAnalyzer.service.ATSAnalyzerService;
import com.shubham.resumeAnalyzer.service.FileStorageService;
import com.shubham.resumeAnalyzer.service.ResumeParserService;
import com.shubham.resumeAnalyzer.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.shubham.resumeAnalyzer.exception.ResumeNotFoundException;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl
        implements ResumeService {

    private final AnalysisResultRepository analysisResultRepository;
    private final ResumeParserService resumeParserService;
    private final FileStorageService fileStorageService;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final ATSAnalyzerService atsAnalyzerService;

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email =
                authentication.getName();

        return userRepository
                .findByEmail(email)
                .orElseThrow();
    }

    @Override
    public ResumeResponse createResume(
            ResumeRequest request) {

        User user = getCurrentUser();

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

        User user = getCurrentUser();

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

    @Override
    public void deleteResume(Long id) {

        User user = getCurrentUser();

        Resume resume =
                resumeRepository
                        .findByIdAndUser(
                                id,
                                user
                        )
                        .orElseThrow(() ->
                                new ResumeNotFoundException(
                                        "Resume not found"
                                ));

        resumeRepository.delete(resume);
    }
    @Override
    public ResumeResponse getResumeById(
            Long id) {

        User user = getCurrentUser();

        Resume resume =
                resumeRepository
                        .findByIdAndUser(
                                id,
                                user
                        )
                        .orElseThrow(() ->
                                new ResumeNotFoundException(
                                        "Resume not found"
                                ));

        return ResumeResponse.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .status(
                        resume.getStatus().name()
                )
                .build();
    }
    @Override
    public ResumeResponse updateResume(
            Long id,
            ResumeRequest request) {

        User user = getCurrentUser();

        Resume resume =
                resumeRepository
                        .findByIdAndUser(
                                id,
                                user
                        )
                        .orElseThrow(() ->
                                new ResumeNotFoundException(
                                        "Resume not found"
                                ));

        resume.setTitle(
                request.getTitle()
        );

        resume.setUpdatedAt(
                LocalDateTime.now()
        );

        Resume updatedResume =
                resumeRepository.save(resume);

        return ResumeResponse.builder()
                .id(updatedResume.getId())
                .title(updatedResume.getTitle())
                .status(
                        updatedResume
                                .getStatus()
                                .name()
                )
                .build();
    }
    @Override
    public ResumeResponse uploadResume(
            String title,
            MultipartFile file) {

        User user = getCurrentUser();

        String filePath =
                fileStorageService
                        .storeFile(file);
        String extractedText =
                resumeParserService
                        .extractText(filePath);

        Resume resume =
                Resume.builder()
                        .title(title)
                        .fileName(
                                file.getOriginalFilename()
                        )
                        .filePath(filePath)
                        .extractedText(
                                extractedText
                        )
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
                resumeRepository.save(
                        resume
                );

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
    public AnalysisResponse analyzeResume(
            Long resumeId) {

        User user = getCurrentUser();

        Resume resume =
                resumeRepository
                        .findByIdAndUser(
                                resumeId,
                                user
                        )
                        .orElseThrow(() ->
                                new ResumeNotFoundException(
                                        "Resume not found"
                                ));

        AnalysisResponse response =
                atsAnalyzerService
                        .analyzeResume(
                                resume.getExtractedText()
                        );

        AnalysisResult analysisResult =
                AnalysisResult.builder()
                        .score(
                                response.getScore()
                        )
                        .matchedSkills(
                                String.join(
                                        ",",
                                        response.getMatchedSkills()
                                )
                        )
                        .missingSkills(
                                String.join(
                                        ",",
                                        response.getMissingSkills()
                                )
                        )
                        .analyzedAt(
                                LocalDateTime.now()
                        )
                        .resume(resume)
                        .build();

        analysisResultRepository.save(
                analysisResult
        );

        return response;
    }
    @Override
    public List<AnalysisHistoryResponse>
    getAnalysisHistory(
            Long resumeId) {

        User user = getCurrentUser();

        Resume resume =
                resumeRepository
                        .findByIdAndUser(
                                resumeId,
                                user
                        )
                        .orElseThrow(() ->
                                new ResumeNotFoundException(
                                        "Resume not found"
                                ));

        return analysisResultRepository
                .findByResume(
                        resume
                )
                .stream()
                .map(result ->
                        AnalysisHistoryResponse
                                .builder()
                                .score(
                                        result.getScore()
                                )
                                .matchedSkills(
                                        List.of(
                                                result
                                                        .getMatchedSkills()
                                                        .split(",")
                                        )
                                )
                                .missingSkills(
                                        List.of(
                                                result
                                                        .getMissingSkills()
                                                        .split(",")
                                        )
                                )
                                .analyzedAt(
                                        result
                                                .getAnalyzedAt()
                                )
                                .build()
                )
                .toList();
    }
}