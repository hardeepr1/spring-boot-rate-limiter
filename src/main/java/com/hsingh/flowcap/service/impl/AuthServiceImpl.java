package com.hsingh.flowcap.service.impl;

import com.hsingh.flowcap.dto.LoginRequestDto;
import com.hsingh.flowcap.dto.LoginResponseDto;
import com.hsingh.flowcap.entity.User;
import com.hsingh.flowcap.exception.InvalidLoginCredentialsException;
import com.hsingh.flowcap.repository.UserRepository;
import com.hsingh.flowcap.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmailId(loginRequestDto.getEmailId())
                .orElseThrow(InvalidLoginCredentialsException::new);

        String encodedPassword = user.getPassword();
        String plainTextPassword = loginRequestDto.getPassword();
        Boolean isPasswordMatched = passwordEncoder.matches(plainTextPassword, encodedPassword);
        if (Boolean.FALSE.equals(isPasswordMatched)) {
            throw new InvalidLoginCredentialsException();
        }

        //Todo: Add access token logic and classes
        String accessToken = "mock_access_token";
        return LoginResponseDto.builder().accessToken(accessToken).build();
    }
}
