package com.shubham.resumeAnalyzer.service.impl;

import com.shubham.resumeAnalyzer.dto.auth.AuthResponse;
import com.shubham.resumeAnalyzer.dto.auth.RegisterRequest;
import com.shubham.resumeAnalyzer.entity.Role;
import com.shubham.resumeAnalyzer.entity.User;
import com.shubham.resumeAnalyzer.exception.UserAlreadyExistsException;
import com.shubham.resumeAnalyzer.repository.UserRepository;
import com.shubham.resumeAnalyzer.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(
                    "Email already registered"
            );
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(Role.ROLE_USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token("Registration Successful")
                .build();
    }
}