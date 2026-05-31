package com.shubham.resumeAnalyzer.dto.analysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisResponse
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private int score;

    private List<String> matchedSkills;

    private List<String> missingSkills;
}