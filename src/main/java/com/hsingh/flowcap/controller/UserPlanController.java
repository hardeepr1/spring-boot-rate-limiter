package com.hsingh.flowcap.controller;

import com.hsingh.flowcap.dto.PlanCreateRequestDto;
import com.hsingh.flowcap.dto.PlanResponseDto;
import com.hsingh.flowcap.dto.PlanUpdateRequestDto;
import com.hsingh.flowcap.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        String userId = "mock/user_id";
        return ResponseEntity.ok(planService.getUserPlan(userId));
    }

    @PutMapping("/plan")
    public ResponseEntity<Void> updatePlan(
            @RequestBody PlanUpdateRequestDto planUpdateRequestDto) {
        String userId = "mock_user_id";
        planService.updateUserPlan(userId, planUpdateRequestDto.getPlanId());
        return ResponseEntity.ok().build();
    }

    //Todo: add admin role and this endpoint should only be for admin user
    @PostMapping("/plan")
    public ResponseEntity<Void> createPlan(
            @RequestBody PlanCreateRequestDto planCreateRequestDto) {
        planService.createPlan(planCreateRequestDto);
        return ResponseEntity.ok().build();
    }
}
