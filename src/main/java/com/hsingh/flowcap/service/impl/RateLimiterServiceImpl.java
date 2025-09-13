package com.hsingh.flowcap.service.impl;

import com.hsingh.flowcap.algorithm.RateLimiter;
import com.hsingh.flowcap.factory.RateLimiterFactory;
import com.hsingh.flowcap.service.RateLimiterService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RateLimiterServiceImpl implements RateLimiterService {

    @Autowired
    private RateLimiterFactory factory;

    @Value("${ratelimiter.algorithm}")
    private String algorithmName;

    private RateLimiter rateLimiter;

    @PostConstruct
    public void init() {
        this.rateLimiter = factory.getRateLimiter(algorithmName);
    }

    //Just Mock endpoint to check if run time beans are loaded corrected correctly.
    @Override
    public boolean isAllowed() {
        return this.rateLimiter.isAllowed("");
    }


}
