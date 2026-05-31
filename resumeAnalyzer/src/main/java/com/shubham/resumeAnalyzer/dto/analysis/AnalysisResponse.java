package com.shubham.resumeAnalyzer.dto.analysis;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AnalysisResponse {

    private int score;

    private List<String> matchedSkills;

    private List<String> missingSkills;
}