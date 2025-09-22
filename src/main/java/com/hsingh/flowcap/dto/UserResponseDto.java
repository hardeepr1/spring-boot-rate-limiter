package com.hsingh.flowcap.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponseDto {
    private UUID id;
    private String emailId;
    private String planName;
    private String createdAt;
}
