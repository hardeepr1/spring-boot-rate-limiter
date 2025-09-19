package com.hsingh.flowcap.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class PlanResponseDto {
    private String id;
    private String name;
    private String description;
    private int rateLimit;
    private String timeWindow; // day, hour, minute
}
