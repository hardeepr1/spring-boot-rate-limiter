package com.hsingh.flowcap.service;

import com.hsingh.flowcap.dto.UserCreateDto;
import com.hsingh.flowcap.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    void createUser(UserCreateDto user);
    List<UserResponseDto> getAllUsers();
}
