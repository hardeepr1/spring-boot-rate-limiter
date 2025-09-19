package com.hsingh.flowcap.service.impl;

import com.hsingh.flowcap.dto.PlanResponseDto;
import com.hsingh.flowcap.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Override
    public List<PlanResponseDto> retrieve() {
        return List.of();
    }

    @Override
    public PlanResponseDto getUserPlan(String userId) {
        return null;
    }
}
