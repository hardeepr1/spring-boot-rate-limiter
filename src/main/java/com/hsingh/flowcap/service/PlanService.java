package com.hsingh.flowcap.service;

import com.hsingh.flowcap.dto.PlanResponseDto;

import java.util.List;

public interface PlanService {
    List<PlanResponseDto> retrieve();
    PlanResponseDto getUserPlan(String userId);
}
