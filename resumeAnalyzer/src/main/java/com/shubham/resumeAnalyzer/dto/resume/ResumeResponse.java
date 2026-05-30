package com.shubham.resumeAnalyzer.dto.resume;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResumeResponse {

    private Long id;

    private String title;

    private String status;
}