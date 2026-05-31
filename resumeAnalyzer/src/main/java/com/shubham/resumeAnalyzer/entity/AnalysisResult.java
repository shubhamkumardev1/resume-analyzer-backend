package com.shubham.resumeAnalyzer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "analysis_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    @Column(columnDefinition = "TEXT")
    private String matchedSkills;

    @Column(columnDefinition = "TEXT")
    private String missingSkills;

    private LocalDateTime analyzedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;
}