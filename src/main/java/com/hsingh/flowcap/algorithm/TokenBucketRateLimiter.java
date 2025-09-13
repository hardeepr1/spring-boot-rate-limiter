package com.hsingh.flowcap.algorithm;

import org.springframework.stereotype.Component;

@Component
public class TokenBucketRateLimiter implements RateLimiter{
    @Override
    public String getName() { return "tokenbucket"; }

    @Override
    public boolean isAllowed(String key) {
        System.out.println("Here we are in the token bucket");
        return false;
    }
}
