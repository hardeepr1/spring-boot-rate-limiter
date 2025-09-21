package com.hsingh.flowcap.service;

import com.hsingh.flowcap.dto.PlanCreateRequestDto;
import com.hsingh.flowcap.dto.PlanResponseDto;

import java.util.List;

public interface PlanService {
    List<PlanResponseDto> retrieve();
    PlanResponseDto getUserPlan(String userId);
    void updateUserPlan(String userId, String planId);
    void createPlan(PlanCreateRequestDto plan);
}
