package com.hsingh.flowcap.service.impl;

import com.hsingh.flowcap.dto.PlanCreateRequestDto;
import com.hsingh.flowcap.dto.PlanResponseDto;
import com.hsingh.flowcap.entity.Plan;
import com.hsingh.flowcap.entity.User;
import com.hsingh.flowcap.repository.UserRepository;
import com.hsingh.flowcap.repository.UserPlanRepository;
import com.hsingh.flowcap.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlanServiceImpl implements PlanService {

    private final UserPlanRepository userPlanRepository;
    private final UserRepository userRepository;

    @Override
    public List<PlanResponseDto> retrieve() {
        return userPlanRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlanResponseDto getUserPlan(String userId) {
        UUID uuid = UUID.fromString(userId);
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Plan plan = user.getPlan();
        if (plan == null) {
            throw new RuntimeException("Plan not assigned to user");
        }

        return toDto(plan);
    }

    @Override
    public void updateUserPlan(String userId, String planId) {
        return;
    }

    @Override
    public void createPlan(PlanCreateRequestDto planCreateRequestDto) {
        Plan plan = new Plan();
        plan.setName(planCreateRequestDto.getName());
        plan.setDescription(planCreateRequestDto.getDescription());
        plan.setRateLimit(planCreateRequestDto.getRateLimit());
        plan.setTimeWindow(planCreateRequestDto.getTimeWindow());

        userPlanRepository.save(plan);
    }

    private PlanResponseDto toDto(Plan plan) {
        PlanResponseDto dto = new PlanResponseDto();
        dto.setId(plan.getId().toString());
        dto.setName(plan.getName());
        dto.setDescription(plan.getDescription());
        dto.setRateLimit(plan.getRateLimit());
        dto.setTimeWindow(plan.getTimeWindow());
        return dto;
    }
}
