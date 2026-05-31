package com.shubham.resumeAnalyzer.service;

import com.shubham.resumeAnalyzer.dto.analysis.AnalysisResponse;

public interface ATSAnalyzerService {

    AnalysisResponse analyzeResume(
            String resumeText
    );
}