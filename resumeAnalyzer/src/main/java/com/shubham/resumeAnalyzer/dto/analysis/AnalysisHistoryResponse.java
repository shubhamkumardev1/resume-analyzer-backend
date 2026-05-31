package com.shubham.resumeAnalyzer.dto.analysis;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AnalysisHistoryResponse {

    private Integer score;

    private List<String> matchedSkills;

    private List<String> missingSkills;

    private LocalDateTime analyzedAt;
}