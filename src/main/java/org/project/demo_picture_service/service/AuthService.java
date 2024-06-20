package org.project.demo_picture_service.service;

import org.project.demo_picture_service.web.dto.auth.JwtRequest;
import org.project.demo_picture_service.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(
            JwtRequest loginRequest
    );

    JwtResponse refresh(
            String refreshToken
    );
}
