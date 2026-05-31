package com.shubham.resumeAnalyzer.service.impl;

import com.shubham.resumeAnalyzer.dto.analysis.AnalysisResponse;
import com.shubham.resumeAnalyzer.service.ATSAnalyzerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ATSAnalyzerServiceImpl
        implements ATSAnalyzerService {

    private static final List<String>
            REQUIRED_SKILLS = List.of(

            "Java",
            "Spring Boot",
            "JWT",
            "Docker",
            "Redis",
            "MySQL",
            "Git"
    );

    @Override
    public AnalysisResponse analyzeResume(
            String resumeText) {

        List<String> matchedSkills =
                new ArrayList<>();

        List<String> missingSkills =
                new ArrayList<>();

        for (String skill :
                REQUIRED_SKILLS) {

            if (resumeText
                    .toLowerCase()
                    .contains(
                            skill.toLowerCase()
                    )) {

                matchedSkills.add(skill);

            } else {

                missingSkills.add(skill);
            }
        }

        int score =
                (matchedSkills.size() * 100)
                        / REQUIRED_SKILLS.size();

        return AnalysisResponse
                .builder()
                .score(score)
                .matchedSkills(
                        matchedSkills
                )
                .missingSkills(
                        missingSkills
                )
                .build();
    }
}