package com.hsingh.flowcap.service.impl;

import com.hsingh.flowcap.dto.LoginRequestDto;
import com.hsingh.flowcap.dto.LoginResponseDto;
import com.hsingh.flowcap.dto.UserCreateDto;
import com.hsingh.flowcap.entity.User;
import com.hsingh.flowcap.exception.InvalidLoginCredentialsException;
import com.hsingh.flowcap.exception.UserAlreadyExistsException;
import com.hsingh.flowcap.repository.AuthRepository;
import com.hsingh.flowcap.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserCreateDto userCreateDto) {
        String emailId = userCreateDto.getEmailId();
        boolean userAccountExistsWithEmailId = authRepository.existsByEmailId(emailId);
        if (Boolean.TRUE.equals(userAccountExistsWithEmailId)) {
            throw new UserAlreadyExistsException("User with the provided email address already exists");
        }
        User user = new User();
        user.setEmailId(emailId);
        String hashedPassword = passwordEncoder.encode(userCreateDto.getPassword());
        user.setPassword(hashedPassword);
        authRepository.save(user);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = authRepository.findByEmailId(loginRequestDto.getEmailId())
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
