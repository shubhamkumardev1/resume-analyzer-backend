package com.shubham.resumeAnalyzer.dto.resume;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResumeRequest {

    @NotBlank
    private String title;
}