package com.hsingh.flowcap.service;

import com.hsingh.flowcap.dto.UserCreateDto;
import com.hsingh.flowcap.entity.User;
import com.hsingh.flowcap.exception.UserAlreadyExistsException;
import com.hsingh.flowcap.repository.UserRepository;
import com.hsingh.flowcap.service.impl.AuthServiceImpl;
import com.hsingh.flowcap.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void testCreateUser_Success(){
        // Arrange
        UserCreateDto dto = new UserCreateDto();
        dto.setEmailId("hraike@mock.com");
        dto.setPassword("plainTextPassword");

        when(userRepository.existsByEmailId("hraike@mock.com")).thenReturn(false);
        when(passwordEncoder.encode("plainTextPassword")).thenReturn("hashedPassword");

        userService.createUser(dto);

        // Assert
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertEquals("hraike@mock.com", savedUser.getEmailId());
        assertEquals("hashedPassword", savedUser.getPassword());
    }

    @Test
    void testCreateUser_UserAlreadyExists(){
        // Arrange
        UserCreateDto dto = new UserCreateDto();
        dto.setEmailId("hraike@mock.com");
        dto.setPassword("plainTextPassword");

        when(userRepository.existsByEmailId("hraike@mock.com")).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(dto));
        verify(userRepository, never()).save(any(User.class));
    }
}
