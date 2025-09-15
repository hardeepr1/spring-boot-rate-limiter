package com.hsingh.flowcap.controller;


import com.hsingh.flowcap.dto.LoginRequestDto;
import com.hsingh.flowcap.dto.LoginResponseDto;
import com.hsingh.flowcap.dto.UserCreateDto;
import com.hsingh.flowcap.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;

    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody final UserCreateDto userCreateRequest) {
        authService.createUser(userCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody final LoginRequestDto loginRequestDto) {
        LoginResponseDto response = authService.login(loginRequestDto);
        return ResponseEntity.ok(response);
    }
}
