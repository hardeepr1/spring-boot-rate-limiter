package com.hsingh.flowcap.service;

import com.hsingh.flowcap.dto.LoginRequestDto;
import com.hsingh.flowcap.dto.LoginResponseDto;
import com.hsingh.flowcap.dto.UserCreateDto;

public interface AuthService {
    void createUser(UserCreateDto user);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
