package com.hsingh.flowcap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanCreateRequestDto {
    private String name;
    private String description;
    private int rateLimit;
    private String timeWindow; // e.g., "hour", "minute", "day"
}
