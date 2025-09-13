package com.hsingh.flowcap.service.impl;

import com.hsingh.flowcap.dto.UserDto;
import com.hsingh.flowcap.entity.User;
import com.hsingh.flowcap.exception.UserAlreadyExistsException;
import com.hsingh.flowcap.repository.AuthRepository;
import com.hsingh.flowcap.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private AuthRepository authRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDto userDto) {
        String emailId = userDto.getEmailId();
        boolean userAccountExistsWithEmailId = authRepository.existsByEmailId(emailId);
        if (Boolean.TRUE.equals(userAccountExistsWithEmailId)) {
            throw new UserAlreadyExistsException("User with the provided email address already exists");
        }
        User user = new User();
        user.setEmailId(emailId);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        authRepository.save(user);
    }
}
