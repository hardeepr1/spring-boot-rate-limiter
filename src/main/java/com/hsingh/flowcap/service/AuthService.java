package com.hsingh.flowcap.service;

import com.hsingh.flowcap.dto.LoginRequestDto;
import com.hsingh.flowcap.dto.LoginResponseDto;
import com.hsingh.flowcap.dto.UserCreateDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
