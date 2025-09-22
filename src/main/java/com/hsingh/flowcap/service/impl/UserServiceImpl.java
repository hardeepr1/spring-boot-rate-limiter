package com.hsingh.flowcap.service.impl;

import com.hsingh.flowcap.dto.UserCreateDto;
import com.hsingh.flowcap.dto.UserResponseDto;
import com.hsingh.flowcap.entity.User;
import com.hsingh.flowcap.exception.UserAlreadyExistsException;
import com.hsingh.flowcap.repository.UserRepository;
import com.hsingh.flowcap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(UserCreateDto userCreateDto) {
        String emailId = userCreateDto.getEmailId();
        boolean userAccountExistsWithEmailId = userRepository.existsByEmailId(emailId);
        if (Boolean.TRUE.equals(userAccountExistsWithEmailId)) {
            throw new UserAlreadyExistsException("User with the provided email address already exists");
        }
        User user = new User();
        user.setEmailId(emailId);
        String hashedPassword = passwordEncoder.encode(userCreateDto.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    private UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmailId(user.getEmailId());
        dto.setPlanName(user.getPlan() != null ? user.getPlan().getName() : null);
        dto.setCreatedAt(user.getCreatedAt().toString());
        return dto;
    }
}

