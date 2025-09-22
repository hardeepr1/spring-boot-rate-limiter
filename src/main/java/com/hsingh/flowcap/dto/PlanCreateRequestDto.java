package com.hsingh.flowcap.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanCreateRequestDto {
    @NotBlank(message = "email id must not be empty")
    private String name;

    @NotBlank(message = "email id must not be empty")
    private String description;

    @Min(value = 1, message = "Rate limit must be at least 1")
    private int rateLimit;

    @NotBlank(message = "Time window must not be empty")
    @Pattern(
            regexp = "^(minute|hour|day)$",
            message = "Time window must be one of: minute, hour, day"
    )
    private String timeWindow;

}
