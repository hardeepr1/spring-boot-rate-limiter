package com.hsingh.flowcap.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(name="id", nullable = false, unique = true)
    private UUID id;

    @Column(name="email_id", nullable=false)
    private String emailId;

    @Column(name="password", nullable = false)
    private String password;

    @Setter(AccessLevel.NONE)
    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @PrePersist
    void onCreate(){
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}
