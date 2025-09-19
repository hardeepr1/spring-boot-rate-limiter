package com.hsingh.flowcap.controller;

import com.hsingh.flowcap.dto.PlanResponseDto;
import com.hsingh.flowcap.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/v1/user")
@RequiredArgsConstructor
public class UserPlanController {
    private final PlanService planService;

    @GetMapping("/plans")
    public ResponseEntity<List<PlanResponseDto>> getAvailablePlans() {
        return ResponseEntity.ok(planService.retrieve());
    }

    @GetMapping("/plan")
    public ResponseEntity<PlanResponseDto> getCurrentUserPlan() {
        String userId = "abc";
        return ResponseEntity.ok(planService.getUserPlan(userId));
    }
}
