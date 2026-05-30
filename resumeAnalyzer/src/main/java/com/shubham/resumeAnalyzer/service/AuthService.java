package com.shubham.resumeAnalyzer.service;

import com.shubham.resumeAnalyzer.dto.auth.AuthResponse;
import com.shubham.resumeAnalyzer.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
}
