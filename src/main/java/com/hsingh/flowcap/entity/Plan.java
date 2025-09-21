package com.hsingh.flowcap.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="plans")
public class Plan {

    @Id
    @Column(name="id", nullable = false, unique = true)
    private UUID id;

    @Column(name="name", nullable = false, unique = true)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="rate_limit", nullable = false)
    private int rateLimit;

    @Column(name="time_window", nullable = false)
    private String timeWindow;            // e.g., "hour", "minute", "day"

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC);
        this.updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}
