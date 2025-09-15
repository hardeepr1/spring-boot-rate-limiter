package com.hsingh.flowcap.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class LoginRequestDto {
    @NotBlank(message = "email id must not be empty")
    @Email(message = "email id must be of valid format")
    private String emailId;

    @NotBlank(message = "password must not be empty")
    private String password;
}
