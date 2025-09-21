package com.hsingh.flowcap.repository;

import com.hsingh.flowcap.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserPlanRepository extends JpaRepository<Plan, UUID> {
}
